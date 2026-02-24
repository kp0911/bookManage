import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String grade; // "NORMAL", "VIP" 등 (나중에 Enum으로 업그레이드하기 좋습니다)
    private List<Book> rentedBooks;

    public User(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.rentedBooks = new ArrayList<>();
    }

    // 등급별 대여 가능 일수 반환 메서드 (다형성이나 if-else로 구현)
    public int getRentalDays() {
        if (grade.equals("VIP")) {
            return 14; // VIP는 14일
        }
        return 7; // 기본은 7일
    }

    //geter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }

    //setter

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setRentedBooks(List<Book> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", 이름='" + name + '\'' +
                ", 회원 등급='" + grade + '\'' +
                ", 대출 도서=" + rentedBooks +
                '}';
    }
}