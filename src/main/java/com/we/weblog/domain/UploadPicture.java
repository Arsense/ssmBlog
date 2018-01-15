package com.we.weblog.domain;

public class UploadPicture {


   private  int     success;        //上传的状态 0失败 1成功
   private  String  message;     //回传消息
   private  String  urlPath;     //上传成功后的路径



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

   public String getUrlPath() {
      return urlPath;
   }

   public void setUrlPath(String urlPath) {
      this.urlPath = urlPath;
   }



}
