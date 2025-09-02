import json, base64, gzip, binascii, pathlib

harPath = "flows.har"
matchUrl = "https://app-analytics-services.com/sdk-exp"
outDir = pathlib.Path("decoded"); outDir.mkdir(exist_ok=True)

def maybeGunzip(b):
    try:
        return gzip.decompress(b)
    except Exception:
        return None

with open(harPath, "r", encoding="utf-8") as f:
    har = json.load(f)

idx = -1
for i, e in enumerate(har["log"]["entries"]):
    if e.get("request", {}).get("url") == matchUrl:
        idx = i; break
if idx == -1:
    raise SystemExit("entry not found")

entry = har["log"]["entries"][idx]
req = entry["request"]
res = entry["response"]

reqText = req.get("postData", {}).get("text", "")
reqBytes = reqText.encode("latin-1")  # 1:1 codepoint→byte
reqRawPath = outDir / "request.raw.pb"
reqRawPath.write_bytes(reqBytes)
reqGun = maybeGunzip(reqBytes)
if reqGun:
    (outDir / "request.pb").write_bytes(reqGun)

resB64 = res.get("content", {}).get("text", "")
resBytes = base64.b64decode(resB64) if resB64 else b""
(outDir / "response.raw.bin").write_bytes(resBytes)
resGun = maybeGunzip(resBytes)
if resGun:
    (outDir / "response.pb").write_bytes(resGun)

def hexdump(b, n=32): 
    return binascii.hexlify(b[:n]).decode()
print("request.raw.pb head:", hexdump(reqBytes))
print("request.pb head:", hexdump(reqGun or b""))
print("response.raw.bin head:", hexdump(resBytes))
print("response.pb head:", hexdump(resGun or b""))
print(f"written to: {outDir.resolve()}")
