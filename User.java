package BankManage;
class User {
    private String username;
    private String password;
    private Account account;

    public User(String username, String password, Account account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public Account getAccount() {
        return account;
    }
}

