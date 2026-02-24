import java.time.LocalDate;

public class Book {
    private String id;        // 도서 번호
    private String category;  // 카테고리
    private String title;     // 제목
    private boolean isRented; // 대출 여부 (false: 대출가능, true: 대출중)
    private LocalDate localDate; // 대출 기간

    public Book(String id, String category, String title, boolean isRented){
        this.id = id;
        this.category = category;
        this.title = title;
        this.isRented = isRented;
        this.localDate = null;
    }

    //getter
    public String getTitle() {
        return title;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getId() {
        return id;
    }

    public String getCategory() { return category; }

    public LocalDate getLocalDate() {
        return localDate;
    }

    //setter
    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public boolean isRentable() {
        return true; // 기본적으로 책은 대출 가능함
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID='" + id + '\'' +
                ", 카테고리='" + category + '\'' +
                ", 제목='" + title + '\'' +
                ", 렌트 여부=" + isRented +
                ", 반납 예정일=" + localDate +
                '}';
    }
}