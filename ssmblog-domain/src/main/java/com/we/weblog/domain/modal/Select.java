package com.we.weblog.domain.modal;

public class Select {

    //   格式 {code:'1' ,  label:'男' ,checked:false},

    private String code;
    //select选项的内容
    private String label;
    //select的值
    private boolean checked = false;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
