
package com.we.weblog.tool;


//这是CTF的一道密码题 放这儿将来可能做加密算法
public class Base16 {

    //猪圈密码
    public static void main(String[] args) {
        String temp = "ocjp{zkirjwmo-ollj-nmlw-joxi-tmolnrnotvms}";
        sortPig(temp);

 }
    /**
     * 猪圈
     * @param code
     */
    public static void sortPig(String code){
        String encode = "abcdefghistvu";
        String decode = "jklmnopqrwxzy";

        for(int i = 0;i < code.length();i++){
            char s= code.charAt(i);
            int index = decode.indexOf(s);
            if(index>=0){
                System.out.print(encode.charAt(index));
            }else{
                index = encode.indexOf(s);
                if(index >=0){
                    System.out.print(decode.charAt(index));
                }else{
                    System.out.print(s);
                }
            }



        }
    }



}
