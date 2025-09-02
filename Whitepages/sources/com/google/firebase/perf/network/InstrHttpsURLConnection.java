package com.google.firebase.perf.network;

import com.google.firebase.perf.metrics.NetworkRequestMetricBuilder;
import com.google.firebase.perf.util.Timer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public final class InstrHttpsURLConnection extends HttpsURLConnection {
    private final InstrURLConnectionBase delegate;
    private final HttpsURLConnection httpsURLConnection;

    InstrHttpsURLConnection(HttpsURLConnection httpsURLConnection2, Timer timer, NetworkRequestMetricBuilder networkRequestMetricBuilder) {
        super(httpsURLConnection2.getURL());
        this.httpsURLConnection = httpsURLConnection2;
        this.delegate = new InstrURLConnectionBase(httpsURLConnection2, timer, networkRequestMetricBuilder);
    }

    public void connect() throws IOException {
        this.delegate.connect();
    }

    public void disconnect() {
        this.delegate.disconnect();
    }

    public Object getContent() throws IOException {
        return this.delegate.getContent();
    }

    public Object getContent(Class[] clsArr) throws IOException {
        return this.delegate.getContent(clsArr);
    }

    public InputStream getInputStream() throws IOException {
        return this.delegate.getInputStream();
    }

    public long getLastModified() {
        return this.delegate.getLastModified();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.delegate.getOutputStream();
    }

    public Permission getPermission() throws IOException {
        return this.delegate.getPermission();
    }

    public int getResponseCode() throws IOException {
        return this.delegate.getResponseCode();
    }

    public String getResponseMessage() throws IOException {
        return this.delegate.getResponseMessage();
    }

    public long getExpiration() {
        return this.delegate.getExpiration();
    }

    public String getHeaderField(int i) {
        return this.delegate.getHeaderField(i);
    }

    public String getHeaderField(String str) {
        return this.delegate.getHeaderField(str);
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.delegate.getHeaderFieldDate(str, j);
    }

    public int getHeaderFieldInt(String str, int i) {
        return this.delegate.getHeaderFieldInt(str, i);
    }

    public long getHeaderFieldLong(String str, long j) {
        return this.delegate.getHeaderFieldLong(str, j);
    }

    public String getHeaderFieldKey(int i) {
        return this.delegate.getHeaderFieldKey(i);
    }

    public Map<String, List<String>> getHeaderFields() {
        return this.delegate.getHeaderFields();
    }

    public String getContentEncoding() {
        return this.delegate.getContentEncoding();
    }

    public int getContentLength() {
        return this.delegate.getContentLength();
    }

    public long getContentLengthLong() {
        return this.delegate.getContentLengthLong();
    }

    public String getContentType() {
        return this.delegate.getContentType();
    }

    public long getDate() {
        return this.delegate.getDate();
    }

    public void addRequestProperty(String str, String str2) {
        this.delegate.addRequestProperty(str, str2);
    }

    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    public boolean getAllowUserInteraction() {
        return this.delegate.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.delegate.getConnectTimeout();
    }

    public boolean getDefaultUseCaches() {
        return this.delegate.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.delegate.getDoInput();
    }

    public boolean getDoOutput() {
        return this.delegate.getDoOutput();
    }

    public InputStream getErrorStream() {
        return this.delegate.getErrorStream();
    }

    public long getIfModifiedSince() {
        return this.delegate.getIfModifiedSince();
    }

    public boolean getInstanceFollowRedirects() {
        return this.delegate.getInstanceFollowRedirects();
    }

    public int getReadTimeout() {
        return this.delegate.getReadTimeout();
    }

    public String getRequestMethod() {
        return this.delegate.getRequestMethod();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.delegate.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.delegate.getRequestProperty(str);
    }

    public URL getURL() {
        return this.delegate.getURL();
    }

    public boolean getUseCaches() {
        return this.delegate.getUseCaches();
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    public void setAllowUserInteraction(boolean z) {
        this.delegate.setAllowUserInteraction(z);
    }

    public void setChunkedStreamingMode(int i) {
        this.delegate.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i) {
        this.delegate.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.delegate.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.delegate.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.delegate.setDoOutput(z);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.delegate.setFixedLengthStreamingMode(i);
    }

    public void setFixedLengthStreamingMode(long j) {
        this.delegate.setFixedLengthStreamingMode(j);
    }

    public void setIfModifiedSince(long j) {
        this.delegate.setIfModifiedSince(j);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.delegate.setInstanceFollowRedirects(z);
    }

    public void setReadTimeout(int i) {
        this.delegate.setReadTimeout(i);
    }

    public void setRequestMethod(String str) throws ProtocolException {
        this.delegate.setRequestMethod(str);
    }

    public void setRequestProperty(String str, String str2) {
        this.delegate.setRequestProperty(str, str2);
    }

    public void setUseCaches(boolean z) {
        this.delegate.setUseCaches(z);
    }

    public String toString() {
        return this.delegate.toString();
    }

    public boolean usingProxy() {
        return this.delegate.usingProxy();
    }

    public String getCipherSuite() {
        return this.httpsURLConnection.getCipherSuite();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.httpsURLConnection.getHostnameVerifier();
    }

    public Certificate[] getLocalCertificates() {
        return this.httpsURLConnection.getLocalCertificates();
    }

    public Principal getLocalPrincipal() {
        return this.httpsURLConnection.getLocalPrincipal();
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.httpsURLConnection.getPeerPrincipal();
    }

    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.httpsURLConnection.getServerCertificates();
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.httpsURLConnection.getSSLSocketFactory();
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.httpsURLConnection.setHostnameVerifier(hostnameVerifier);
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
    }
}
