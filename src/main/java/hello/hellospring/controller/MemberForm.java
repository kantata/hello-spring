package hello.hellospring.controller;

public class MemberForm {
    private String name; // createMemberForm.html 내 name => name 찾음

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
