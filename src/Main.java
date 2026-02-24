import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    //static List<Book> bookMap = new ArrayList<>();
    static HashMap<String, Book> bookMap = new HashMap<>();
    static HashMap<String, User> userMap = new HashMap<>();

    public static void main(String[] args) {
        initData(); // 프로그램 시작 시 초기 데이터 30권 세팅

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== 도서 관리 시스템 ===");
            System.out.println("1. 전체 도서 조회");
            System.out.println("2. 도서 검색 (제목)");
            System.out.println("3. 도서 대출 및 반납 기능");
            System.out.println("4. 회원 추가");
            System.out.println("5. 회원 출력");
            System.out.println("6. 프로그램 종료");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    printAllBooks();
                    break;
                case "2":
                    System.out.println("찾고자 하는 책의 이름을 검색 해주세요.");
                    String bookName = scanner.nextLine();
                    printOneBook(bookName);
                    break;
                case "3":
                    System.out.println("1.대출 및 2.반납을 선택 해주세요.");
                    String choice2 = scanner.nextLine();
                    try {
                        if(choice2.equals("1")){
                            checkOutBook(scanner);
                        }else if(choice2.equals("2")){
                            checkInBook(scanner);
                        }

                    }catch (BookNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("회원의 이름과 등급을 입력 해주세요.");
                    registerUser(scanner);
                    break;
                case "5":
                    System.out.println("회원의 정보를 출력 합니다.");
                    showUser();
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
        scanner.close();
    }

    // 3개 카테고리, 각 10권씩 총 30권의 더미 데이터를 세팅하는 메서드
    public static void initData() {

        for(int i = 1; i <= 20; i++){
            String bookId = "B" + i;
            bookMap.put(bookId, new Book(bookId, "일반", "일반도서" + i, false));
        }

        // 21~30번은 참고 자료 (다형성: Book 타입의 맵에 ReferenceBook을 넣을 수 있음!)
        for(int i = 21; i <= 30; i++){
            String bookId = "B" + i;
            bookMap.put(bookId, new ReferenceBook(bookId, "참고자료", "백과사전" + i, false, "제1열람실"));
        }

        userMap.put("U1", new User("U1", "관리자", "vip"));
    }

    public static void printAllBooks() {
        System.out.println("\n--- 전체 도서 목록 ---");
        for (Book book : bookMap.values()){
            System.out.println(book);
        }
    }

    public static void printOneBook(String bookName){
        for (Book book : bookMap.values()){
            if (book.getTitle().equals(bookName)) {
                System.out.println("검색 결과: " + book);
                return;
            }
        }
        System.out.println("해당 제목의 책은 존재 하지 않습니다.");
    }

    //도서 반납 메서드
    public static void checkInBook(Scanner scanner){
        System.out.println("반납할 책을 선택 해주세요.");

        String checkInId = scanner.nextLine();
        Book targetBook = bookMap.get(checkInId);

        if (targetBook == null) {
            throw new BookNotFoundException("해당 번호의 도서는 존재하지 않습니다.");
        }

        if (targetBook.isRented()){
            targetBook.setRented(false);
            targetBook.setLocalDate(null);
            System.out.println("[" + targetBook.getTitle() + "] 반납이 완료되었습니다.");
            return;
        }else {
            System.out.println("해당 제목의 책은 이미 반납 되어 있습니다.");
        }
/*
        for(Book book : bookMap.values()){
            if (book.getTitle().equals(checkIn) && book.isRented()) {
                book.setRented(false);
                System.out.println("[" + book.getTitle() + "] 반납이 완료되었습니다.");
                return;
            }
        }
        System.out.println("해당 제목의 책은 존재하지 않거나 처리할 수 없습니다.");
 */
    }

    //도서 대출 매서드
    public static void checkOutBook(Scanner scanner){
        System.out.println("대출할 책을 선택 해주세요.");

        String checkOutId = scanner.nextLine();
        Book targetBook = bookMap.get(checkOutId);

        if (targetBook == null) {
            throw new BookNotFoundException("해당 번호의 도서는 존재하지 않습니다.");
        }

        if (!targetBook.isRentable()) {
            System.out.println("해당 도서는 열람실 전용 참고자료이므로 대출할 수 없습니다.");
            return;
        }

        if (!targetBook.isRented()){
            targetBook.setRented(true);
            targetBook.setLocalDate(LocalDate.now().plusDays(7));
            System.out.println("[" + targetBook.getTitle() + "] 대출이 완료되었습니다. (반납 예정일: " + targetBook.getLocalDate() + ")");
               return;
        }else {
            System.out.println("해당 제목의 책은 이미 대출 되어 있습니다.");
        }
    }

    public static void showUser(){
        for(User user : userMap.values()){
            System.out.println(user);
        }
    }

    //사용자 등록
    public static void registerUser(Scanner scanner){
        while (true){
            System.out.print("이름을 입력 해주세요(나가시려면 x를 입력 해주세요): ");
            String name = scanner.nextLine();

            if(name.equals("x")){
                System.out.println("회원 등록을 종료합니다.");
                break;
            }

            System.out.print("등급을 입력 해주세요(nomal, vip): ");
            String grade = scanner.nextLine();

            if(grade.equals("vip") || grade.equals("nomal")){
                // 2. [수정] ID 발급을 루프 안쪽으로 이동하여 회원마다 고유한 ID 부여
                // (기존에 관리자 U1이 있으므로, size가 1이면 다음 회원은 U2가 됨)
                String userId = "U" + (userMap.size() + 1);

                userMap.put(userId, new User(userId, name, grade));
                System.out.println(">> [" + name + "] 님이 성공적으로 등록되었습니다. (부여된 ID: " + userId + ")\n");
            }
            else {
                System.out.println("등급을 잘못 입력 했습니다. 다시 입력 해주세요.\n");
            }
        }
    }
}