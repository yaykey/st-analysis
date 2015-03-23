package com.st.framework.module.stock;

public class FactProxyKey {
    private String proxyIp;

    private Integer proxyPort;

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp == null ? null : proxyIp.trim();
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FactProxyKey other = (FactProxyKey) that;
        return (this.getProxyIp() == null ? other.getProxyIp() == null : this.getProxyIp().equals(other.getProxyIp()))
            && (this.getProxyPort() == null ? other.getProxyPort() == null : this.getProxyPort().equals(other.getProxyPort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProxyIp() == null) ? 0 : getProxyIp().hashCode());
        result = prime * result + ((getProxyPort() == null) ? 0 : getProxyPort().hashCode());
        return result;
    }
}