package cn.uestc.pojo;

public class Log {

    private Integer id;
    private String acconOut;
    private String acconIn;
    private Double balance;

    @Override
    public String toString() {
        return "log{" +
                "id=" + id +
                ", acconOut='" + acconOut + '\'' +
                ", acconIn='" + acconIn + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcconOut() {
        return acconOut;
    }

    public void setAcconOut(String acconOut) {
        this.acconOut = acconOut;
    }

    public String getAcconIn() {
        return acconIn;
    }

    public void setAcconIn(String acconIn) {
        this.acconIn = acconIn;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
