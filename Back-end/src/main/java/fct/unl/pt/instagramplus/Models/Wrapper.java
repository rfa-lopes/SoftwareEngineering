package fct.unl.pt.instagramplus.Models;

import lombok.Data;

@Data
public class Wrapper<T> {

    String cookie;

    T data;

    public Wrapper() { }

    public Wrapper(WrapperBuilder builder) {
        this.cookie = builder.cookie;
        this.data = (T)builder.data;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class WrapperBuilder<T> {
        private String cookie;

        private T data;

        public WrapperBuilder(T response){
            this.data = response;
        }

        public WrapperBuilder setCoookie(String coookie) {
            this.cookie = coookie;
            return this;
        }

        public Wrapper build(){
            return new Wrapper(this);
        }
    }
}
