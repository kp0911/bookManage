public class ReferenceBook extends Book {
    private String readingRoom; // 열람실 번호 (예: 제1열람실)

    // 생성자
    public ReferenceBook(String id, String category, String title, boolean isRented, String readingRoom) {
        // 부모(Book)의 생성자 호출
        super(id, category, title, isRented);
        this.readingRoom = readingRoom;
    }

    // 💡 핵심: 부모의 메서드를 덮어써서 참고자료는 대출이 불가능하게 만듦
    @Override
    public boolean isRentable() {
        return false;
    }

    // 출력 형태도 덮어쓰기
    @Override
    public String toString() {
        return "[참고자료] " + super.getTitle() + " (열람실: " + readingRoom + ") - 대출불가";
    }
}