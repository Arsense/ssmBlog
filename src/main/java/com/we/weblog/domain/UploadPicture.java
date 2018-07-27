package com.we.weblog.domain;



public class UploadPicture {

   private int success;//0 表示上传失败，1 表示上传成功
   private  String  message;     //回传消息
   private  String  url;     //上传成功后的路径

   public int getSuccess() {
      return success;
   }

   public void setSuccess(int success) {
      this.success = success;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

    @Override
    public String toString() {
        return "UploadPicture{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
