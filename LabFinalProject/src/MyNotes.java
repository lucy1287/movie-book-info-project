//////////////////////
///이은재(2016969)
///2021-12-18
///LabFinalProject: 개인 영화/도서 관리 프로그램 작성
/////////////////////
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import java.util.Vector;
import java.io.*;
import java.util.Calendar;

class Item implements Serializable{   //Item 클래스 작성
	String name, year, imgPath, summary, review;
	int rate;
	public Item(String name, String year, String imgPath, int rate, String summary, String review) {
		this.name = name;
		this.year = year;
		this.imgPath = imgPath;
		this.rate = rate;
		this.summary = summary;
		this.review = review;
	}
	public String getImgPath() {  //이미지 경로 리턴하는 getImgPath() 작성
		return imgPath;
	}
}

class Movie extends Item implements Serializable{  //Item 클래스를 상속받는 Movie 클래스 작성
	String director, actor, genre, grade;
	public Movie(String name, String director, String actor, String genre, 
	String grade, String year, String imgPath, int rate, String summary, 
	String review) {
		super(name, year, imgPath, rate, summary, review);
		this.director = director;
		this.actor = actor;
		this.genre = genre;
		this.grade = grade;
	}
}

class Book extends Item implements Serializable{  //Item 클래스를 상속받는 Book 클래스 작성
	String author, publisher;
	public Book(String name, String author, String publisher, String year, String imgPath, 
	int rate, String summary, String review) {
			super(name, year, imgPath, rate, summary, review);
			this.author = author;
			this.publisher = publisher;
			}
}

class ItemCollections {   //ItemCollections 클래스 작성
	static Vector<Item> v = new Vector<Item>();  //Item 타입의 객체 저장하는 벡터 v 생성
	static Vector<Movie> vm = new Vector<Movie>();  //Movie 타입의 객체 저장하는 벡터 vm 생성
	static Vector<Book> vb = new Vector<Book>();  //Book 타입의 객체 저장하는 벡터 vb 생성
	
	public static String[] getVNames() {//벡터 v의 요소들의 name들로 구성된 배열 리턴하는 getVNames() 작성
		String[] vNames = new String[v.size()];
		for(int i = 0; i < v.size(); i++)   //v.size()만큼 반복문 돌면서
			vNames[i] = v.get(i).name;     //vNames 배열에 각 요소의 name 저장
		return vNames;
	}
	public static String[] getVmNames() {//벡터 vm의 요소들의 name들로 구성된 배열 리턴하는 getVmNames() 작성
		String[] vmNames = new String[vm.size()];
		for(int i = 0; i < vm.size(); i++)  //vm.size()만큼 반복문 돌면서
			vmNames[i] = vm.get(i).name;    //vmNames 배열에 각 요소의 name 저장
		return vmNames;
	}
	public static String[] getVbNames() {//벡터 vb의 요소들의 name들로 구성된 배열 리턴하는 getVbNames() 작성
		String[] vbNames = new String[vb.size()];
		for(int i = 0; i < vb.size(); i++)   //vb.size()만큼 반복문 돌면서
			vbNames[i] = vb.get(i).name;     //vbNames 배열에 각 요소의 name 저장
		return vbNames; 
	}
	public static void add(Item item) {   //Item 객체를 벡터에 추가하는 add() 작성
		v.add(item);                   //v에 item 추가
		if(item instanceof Movie)      //item이 Movie 타입이면
			vm.add((Movie)item);       //item을 vm에 추가
		else if(item instanceof Book)     //item이 Book 타입이면
			vb.add((Book)item);           //item을 vb에 추가
	}
	//Item 객체가 Movie타입일 때 수정하는 메소드 modifyMovie() 작성
	public static void modifyMovie(Item item, int indexV, int indexVm) {  
		v.set(indexV, item);            //v에서 수정
		vm.set(indexVm, (Movie)item);   //vm에서 수정
	}
	//Item 객체가 Book타입일 때 수정하는 메소드 modifyBook() 작성
	public static void modifyBook(Item item, int indexV, int indexVb) {
		v.set(indexV, item);           //v에서 수정
		vb.set(indexVb, (Book)item);   //vb에서 수정
	}
	public static void remove(Item item) {  //Item 객체를 벡터에서 삭제하는 remove() 작성
		v.remove(item);              //v에서 삭제
		if(item instanceof Movie)    //item이 Movie 타입이면
			vm.remove((Movie)item);  //vm에서 삭제
		else if(item instanceof Book)    //item이 Book 타입이면
			vb.remove((Book)item);       //vb에서 삭제
	}
	public static String[] searchName(String string) {//이름으로 Item 객체 검색하는 searchName() 작성
		String[] result = new String[v.size()];
		int j = 0;
		for(int i = 0; i < v.size(); i++) {  //v.size()만큼 반복문 돌면서
			Item item = v.get(i);           
			if(item.name.contains(string))  //item의 name이 string을 포함하면
				result[j++] = item.name;	//result 배열에 item.name 저장			  
		}
		return result;    //result 배열 리턴
	}
	public static String[] searchRate(int rate) {//별점으로 Item 객체 검색하는 searchRate() 작성
		String[] result = new String[v.size()];
		int j = 0;
		for(int i = 0; i < v.size(); i++) {  //v.size()만큼 반복문 돌면서
			Item item = v.get(i);
			if(item.rate >= rate)    //item의 rate이 매개변수로 받은 rate 이상이면
				result[j++] = item.name;   //result 배열에 item.name 저장	
		}
		return result;   //result 배열 리턴
	}
	public static Item searchEqualName(String string) { //이름 같은 Item 객체 검색하는 메소드 작성
		Item equalItem = null;
		for(int i = 0; i < v.size(); i++) {   //v.size()만큼 반복문 돌면서
			Item item = v.get(i);
			if(item.name.equals(string))  //item의 name이 string과 같다면
				equalItem = item;   //item을 equalItem으로 함
		}
		return equalItem;   //equalItem 리턴
	}
}

class MyLabel extends JLabel implements Runnable {  //스레드 기능 추가된 MyLabel 클래스 작성
	private Thread timerThread = null;
	public MyLabel() {
		setText(makeClockText()); //makeCLockText() 호출하여 MyLabel의 텍스트로 설정
		setFont(new Font("굴림", Font.ITALIC, 30));
		timerThread = new Thread(MyLabel.this);  //Thread 객체 생성
		timerThread.start();    //스레드 동작시킴
	}
	
	public String makeClockText() {  //makeCLockText() 작성
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		
		String clockText = Integer.toString(year)+"년 "+Integer.toString(month)+"월 "
		+Integer.toString(date)+"일 "+Integer.toString(hour)+":"+Integer.toString(min)
		+":"+Integer.toString(second);   //년,월,일,시,분,초 이어붙인 문자열을 clockText에 저장
		return clockText;    //clockText 리턴
	} 
	public void run() {  //run() 재정의
		while(true) {
			try {
				Thread.sleep(1000);  //1초 동안 쉬기
			}
			catch(InterruptedException e){return;}
			setText(makeClockText());   //다시 makeCLockText() 호출하여 MyLabel의 텍스트로 설정
		}
	}
}

class MyDialog extends JDialog {   //JDialog를 상속받는 MyDialog 클래스 작성
	int btnPressed = 0;
	JRadioButton mv, bk;
	JPanel pMovie, pBook;
	JTextField mtf1, mtf2, mtf3, mtf4;
	JTextArea mta1, mta2;
	JTextField btf1, btf2, btf3, btf4;
	JTextArea bta1, bta2;
	JComboBox genreCombo, gradeCombo, mYearCombo, bYearCombo;
	JSlider mSlider, bSlider;
	String[] genres = {"드라마", "공포", "액션", "로맨스", "판타지/SF", "애니메이션"};
	String[] grades = {"전체", "12세 이상", "15세 이상", "청소년 관람 불가"};
	String[] years = {"2016","2017","2018","2019","2020","2021"};
	String selectedGenre = genres[0];
	String selectedGrade = grades[0];
	String mSelectedYear = years[0];
	String bSelectedYear = years[0];
	int indexV, indexVm, indexVb;
	Movie movie;
	Book book;
	public MyDialog(JFrame frame, String title) { //생성자 작성
		super(frame, title, true);
		JPanel pSelect = new JPanel();  //라디오버튼 2개 붙일 pSlect 패널 생성
		ButtonGroup group = new ButtonGroup(); //버튼그룹 객체 생성
		//라디오버튼 2개 생성, 각각에 MyItemListener 등록
		mv = new JRadioButton("Movie", true);   
		mv.addItemListener(new MyItemListener());
		bk = new JRadioButton("Book");
		bk.addItemListener(new MyItemListener());
		group.add(mv);   group.add(bk);
		pSelect.add(mv);  pSelect.add(bk);
		add(pSelect, BorderLayout.NORTH);  //pSelect를 MyDialog의 NORTH영역에 추가
		
		pMovie = new JPanel();   //영화 정보 표시할 pMovie 패널 생성
		pMovie.setLayout(new BorderLayout());
		Border movieBorder = BorderFactory.createTitledBorder("영화 정보");
		pMovie.setBorder(movieBorder);
		add(pMovie, BorderLayout.CENTER);  //pMovie를 MyDialog의 CENTER영역에 추가
		
		JPanel pMovie1 = new JPanel();  //영화 정보의 JLabel들을 붙일 pMovie1 패널 생성 
		pMovie1.setLayout(new GridLayout(0,1));  //pMovie1의 배치관리자를 GridLayout으로 설정
		pMovie1.add(new JLabel("제목"));
		pMovie1.add(new JLabel("감독"));
		pMovie1.add(new JLabel("배우"));
		pMovie1.add(new JLabel("장르"));
		pMovie1.add(new JLabel("등급"));
		pMovie1.add(new JLabel("개봉년도"));
		pMovie1.add(new JLabel("포스터"));
		pMovie1.add(new JLabel("별점"));
		pMovie1.add(new JLabel("줄거리"));
		pMovie1.add(new JLabel("감상평"));
		pMovie.add(pMovie1, BorderLayout.WEST); //pMovie1을 pMovie의 WEST영역에 추가
		
		JPanel pMovie2 = new JPanel();  //영화 정보의 내용 컴포넌트들을 붙일 pMovie2 패널 생성 
		pMovie2.setLayout(new GridLayout(0,1));  //pMovie2의 배치관리자를 GridLayout으로 설정
		//JTextField 객체 3개 생성
		mtf1 = new JTextField();
		pMovie2.add(mtf1);
		mtf2 = new JTextField();
		pMovie2.add(mtf2);
		mtf3 = new JTextField();
		pMovie2.add(mtf3);
		genreCombo = new JComboBox<String>(genres);  //genres 배열로 아이템이 구성된 JComboBox 객체 생성 
		genreCombo.addActionListener(new ActionListener() { //genreCombo에 ActionListener 등록
			public void actionPerformed(ActionEvent e) { 
				int genreIndex = genreCombo.getSelectedIndex();//선택된 아이템의 인덱스를 genreIndex에 저장
				selectedGenre = genres[genreIndex]; 
				//genres배열에서 genreIndex에 해당하는 문자열을 selectGenre에 저장
			}
		});
		pMovie2.add(genreCombo);
		gradeCombo = new JComboBox<String>(grades);  //grades 배열로 아이템이 구성된 JComboBox 객체 생성
		gradeCombo.addActionListener(new ActionListener() {  //gradeCombo에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {
				int gradeIndex = gradeCombo.getSelectedIndex();//선택된 아이템의 인덱스를 gradeIndex에 저장
				selectedGrade = grades[gradeIndex];
				//grades배열에서 gradeIndex에 해당하는 문자열을 selectGrade에 저장
			}
		});
		pMovie2.add(gradeCombo);
		mYearCombo = new JComboBox<String>(years);  //years 배열로 아이템이 구성된 JComboBox 객체 생성
		mYearCombo.addActionListener(new ActionListener() { //mYearCombo에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {
				int yearIndex = mYearCombo.getSelectedIndex();//선택된 아이템의 인덱스를 yearIndex에 저장
				mSelectedYear = years[yearIndex];
				//years배열에서 yearIndex에 해당하는 문자열을 mSelectYear에 저장
			}
		});
		pMovie2.add(mYearCombo);
		
		JPanel pPoster = new JPanel();  //이미지경로 텍스트필드, 불러오기 버튼 붙일 pPoster 패널 생성
		mtf4 = new JTextField(20);   //이미지경로 표시할 JTextField 생성
		pPoster.add(mtf4);
		JButton loadBtn = new JButton("불러오기");  //불러오기 버튼 생성
		loadBtn.addActionListener(new ActionListener() {  //버튼에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();  //JFileChooser 객체 생성
				int ret = chooser.showOpenDialog(null);  //열기 다이얼로그 출력
				if(ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();  
					//사용자가 선택한 파일 경로 반환
					mtf4.setText(pathName);  //pathName을 이미지경로 텍스트필드에 표시
				}
			}
		});
		pPoster.add(loadBtn);
		pMovie2.add(pPoster);  //pPoster를 pMovie2에 추가
		
		mSlider = new JSlider(JSlider.HORIZONTAL,1,10,5);  //JSlider 객체 생성
		mSlider.setPaintLabels(true);
		mSlider.setPaintTicks(true);
		mSlider.setPaintTrack(true);
		mSlider.setMajorTickSpacing(1);
		pMovie2.add(mSlider);  //mSlider를 pMovie2에 추가
		
		mta1 = new JTextArea();  //줄거리 작성 위한 JTextArea 객체 생성
		pMovie2.add(new JScrollPane(mta1));  //mta1을 JScrollPane을 통해서 pMovie2에 추가
		mta2 = new JTextArea();  //감상평 작성 위한 JTextArea 객체 생성
		pMovie2.add(new JScrollPane(mta2));  //mta2를 JScrollPane을 통해서 pMovie2에 추가
		JPanel pOk = new JPanel();   //OK 버튼 붙이기 위한 pOK 패널 생성
		JButton okBtn = new JButton("OK");  //OK 버튼 생성
		okBtn.addActionListener(new ActionListener() {  //okBtn에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {
				//각 컴포넌트들에 입력된 내용을 가져옴
				String name = mtf1.getText();
				String director = mtf2.getText();
				String actor = mtf3.getText();
				String imgPath = mtf4.getText();
				int rate = mSlider.getValue();
				String summery = mta1.getText();
				String review = mta2.getText();
				//가져온 내용들로 Movie 객체 생성
				movie = new Movie(name, director, actor, selectedGenre,
				selectedGrade, mSelectedYear, imgPath, rate, summery, review);
				if(btnPressed == 1)  //전에 추가 버튼이 눌렸다면
					ItemCollections.add(movie);	  //movie를 add() 통해 벡터에 추가
				else if(btnPressed == 2)   //전에 수정 버튼이 눌렸다면
					ItemCollections.modifyMovie(movie, indexV, indexVm);
				    //movie를 modifyMovie() 통해 수정
				//다시 MyDialog 초기화
				mv.setSelected(true);
				mtf1.setText(""); mtf2.setText(""); mtf3.setText(""); mtf4.setText("");
				mta1.setText(""); mta2.setText("");
				genreCombo.setSelectedIndex(0);
				gradeCombo.setSelectedIndex(0);
				mYearCombo.setSelectedIndex(0);
				mSlider.setValue(5);
				setVisible(false);  //MyDialog 보이지 않게 함
				btnPressed = 0;   //btnPressed 다시 0으로 설정
			}
		});	
		pOk.add(okBtn);
		pMovie.add(pMovie2, BorderLayout.CENTER);  //pMovie2를 pMovie의 CENTER영역에 추가
		pMovie.add(pOk, BorderLayout.SOUTH); //pOk를 pMovie의 SOUTH영역에 추가
	
		setSize(400, 600);
	}
	
	private class MyItemListener implements ItemListener{  //MyItemListener 클래스 작성
		public void itemStateChanged(ItemEvent e) {  //라디오 버튼의 선택이 변경된다면
			if(mv.isSelected()) {  //mv가 선택되었을 때
				pMovie.setVisible(true);  //pMovie가 보여지게 함
				pBook.setVisible(false);  //pBook은 보여지지 않게 함
			}
			else if(bk.isSelected()) {  //bk가 선택되었을 때
				pBook = new JPanel();  //도서 정보 표시할 pBook 패널 생성
				pBook.setLayout(new BorderLayout()); 
				Border bookBorder = BorderFactory.createTitledBorder("도서 정보");
				pBook.setBorder(bookBorder);
				add(pBook, BorderLayout.CENTER); //pBook을 MyDialog의 CENTER영역에 추가
					
				JPanel pBook1 = new JPanel(); //도서 정보의 JLabel들을 붙일 pBook1 패널 생성 
				pBook1.setLayout(new GridLayout(0,1)); //pBook1의 배치관리자를 GridLayout으로 설정
				pBook1.add(new JLabel("제목"));
				pBook1.add(new JLabel("저자"));
				pBook1.add(new JLabel("출판사"));
				pBook1.add(new JLabel("출판년도"));
				pBook1.add(new JLabel("책이미지"));
				pBook1.add(new JLabel("별점"));
				pBook1.add(new JLabel("책소개"));
				pBook1.add(new JLabel("감상평"));
				pBook.add(pBook1, BorderLayout.WEST); //pBook1을 pBook의 WEST영역에 추가
				
				JPanel pBook2 = new JPanel(); //도서 정보의 내용 컴포넌트들을 붙일 pBook2 패널 생성 
				pBook2.setLayout(new GridLayout(0,1)); //pBook2의 배치관리자를 GridLayout으로 설정
				//JTextField 3개 생성
				btf1 = new JTextField();
				pBook2.add(btf1);
				btf2 = new JTextField();
				pBook2.add(btf2);
				btf3 = new JTextField();
				pBook2.add(btf3);
				bYearCombo = new JComboBox<String>(years);//years 배열로 아이템이 구성된 JComboBox 객체 생성
				bYearCombo.addActionListener(new ActionListener() { //bYearCombo에 ActionListener 등록
					public void actionPerformed(ActionEvent e) {
						int yearIndex = bYearCombo.getSelectedIndex();//선택된 아이템의 인덱스를 yearIndex에 저장
						bSelectedYear = years[yearIndex];
						//years배열에서 yearIndex에 해당하는 문자열을 bSelectYear에 저장
					}
				});
				pBook2.add(bYearCombo);
				JPanel pPoster = new JPanel();  //이미지경로 텍스트필드, 불러오기 버튼 붙일 pPoster 패널 생성
				btf4 = new JTextField(20);  //이미지경로 표시할 JTextField 생성
				pPoster.add(btf4);
				JButton loadBtn = new JButton("불러오기"); //불러오기 버튼 생성
				loadBtn.addActionListener(new ActionListener() {  //버튼에 ActionListener 등록
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser();  //JFileChooser 객체 생성
						int ret = chooser.showOpenDialog(null);  //열기 다이얼로그 출력
						if(ret == JFileChooser.APPROVE_OPTION) {
							String pathName = chooser.getSelectedFile().getPath();  
							//사용자가 선택한 파일 경로 반환
							btf4.setText(pathName);  //pathName을 이미지경로 텍스트필드에 표시
						}
					}
				});
				pPoster.add(loadBtn);
				pBook2.add(pPoster); //pPoster를 pBook2에 추가
				
				bSlider = new JSlider(JSlider.HORIZONTAL,1,10,5);  //JSLider 객체 생성
				bSlider.setPaintLabels(true);
				bSlider.setPaintTicks(true);
				bSlider.setPaintTrack(true);
				bSlider.setMajorTickSpacing(1);
				pBook2.add(bSlider);  //mSlider를 pBook2에 추가
				
				bta1 = new JTextArea();  //줄거리 작성 위한 JTextArea 객체 생성
				pBook2.add(new JScrollPane(bta1));  //bta1을 JScrollPane을 통해서 pMovie2에 추가
				bta2 = new JTextArea();  //감상평 작성 위한 JTextArea 객체 생성
				pBook2.add(new JScrollPane(bta2));   //bta2를 JScrollPane을 통해서 pMovie2에 추가
				JPanel pOk = new JPanel();  //OK 버튼 붙이기 위한 pOK 패널 생성
				JButton okBtn = new JButton("OK");  //OK 버튼 생성
				okBtn.addActionListener(new ActionListener() {  //okBtn에 ActionListener 등록
					public void actionPerformed(ActionEvent e) {
						//각 컴포넌트들에 입력된 내용을 가져옴
						String name = btf1.getText();
						String author = btf2.getText();
						String publisher = btf3.getText();
						String imgPath = btf4.getText();
						int rate = bSlider.getValue();
						String summery = bta1.getText();
						String review = bta2.getText();
						//가져온 내용들로 Movie 객체 생성
						book = new Book(name, author, publisher, bSelectedYear, 
								imgPath, rate, summery, review);
						if(btnPressed == 1)   //전에 추가 버튼이 눌렸다면
							ItemCollections.add(book);	 //book을 add() 통해 벡터에 추가
						else if(btnPressed == 2)  //전에 수정 버튼이 눌렸다면
							ItemCollections.modifyBook(book, indexV, indexVb);
							//book을 modifyMovie() 통해 수정	
						//다시 MyDialog 초기화
						mv.setSelected(true);
						btf1.setText(""); btf2.setText(""); btf3.setText(""); btf4.setText("");
						bta1.setText(""); bta2.setText("");
						bYearCombo.setSelectedIndex(0); 
						bSlider.setValue(5);
						setVisible(false);  //MyDialog 보이지 않게 함
						btnPressed = 0;   //btnPressed 다시 0으로 설정
					}
				});	
				pOk.add(okBtn);
				pBook.add(pBook2, BorderLayout.CENTER);  //p2를 pBook의 CENTER영역에 추가
				pBook.add(pOk, BorderLayout.SOUTH);   //pOk를 pBook의 SOUTH영역에 추가
				
				pMovie.setVisible(false);  //pMovie가 보여지지 않게 함
				pBook.setVisible(true);    //pBook은 보여지게 함
			}
		}
	}
}

public class MyNotes extends JFrame{  //JFrame을 상속받는 MyNotes 클래스 작성
	JList<String> totalList, movieList, bookList, searchList;
	String[] result = {};
	JTextArea summaryArea, reviewArea;
	Item selectedItem;
	ImgPanel pImg;
	JPanel pInfo;
	public MyNotes() {  //생성자 작성
		setTitle("JAVA 4분반 2016969 이은재");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();   //createMenu() 호출
		Container c = getContentPane();
		c.setLayout(null);  //c의 배치관리자를 null로 설정
		
		JPanel p0 = new JPanel();  //"My Notes" 표시되는 라벨, 현재시간 표시되는 라벨 붙일 p0 패널 생성
		p0.setLayout(null);  //p0의 배치관리자를 null로 설정
		p0.setBounds(0,0,1200,50);
		JLabel lbl = new JLabel("My Notes");  //"My Notes" 표시될 JLabel 객체 생성
		lbl.setBounds(100,0,200,30);
		lbl.setFont(new Font("돋움", Font.ITALIC+Font.BOLD, 30));
		lbl.setForeground(Color.BLUE);
		lbl.setOpaque(true);
		p0.add(lbl);   //p0에 lbl 추가
		MyLabel lblTime = new MyLabel();  //현재 시간 표시될 MyLabel 객체 생성
		lblTime.setBounds(850,0,500,30);
		lblTime.setFont(new Font("굴림", Font.ITALIC, 18));
		p0.add(lblTime);  //p0에 lblTime 추가
		c.add(p0);    //c에 p0 추가
		
		JPanel p1 = new JPanel();  //탭팬, 추가 버튼 붙일 p1 패널 생성
		p1.setLayout(null);   //p1의 배치관리자를 null로 설정
		p1.setBounds(0,50,300,600);
		JTabbedPane pane = createTabbedPane();  //createTabbedPane() 호출
		pane.setBounds(0,0,300,470);
		p1.add(pane);   //p1에 pane 추가
		MyDialog dialog = new MyDialog(this, "입력");  //MyDialog 객체 생성
		JButton addBtn = new JButton("추가");  //추가 버튼 생성
		addBtn.addActionListener(new ActionListener() {  //addBtn에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {  //addBtn 눌리면
				dialog.btnPressed = 1;  //dialog의 멤버변수 btnPressed를 1로 설정
				dialog.setVisible(true);  //dialog가 표시되도록 함
				totalList.setListData(ItemCollections.getVNames());  //totalList에 추가된 것 반영
				movieList.setListData(ItemCollections.getVmNames()); //movieList에 추가된 것 반영
				bookList.setListData(ItemCollections.getVbNames());  //bookList에 추가된 것 반영
			}
		});
		addBtn.setBounds(120,480,70,30);
		p1.add(addBtn);  //addBtn을 p1에 추가
		c.add(p1);  //p1을 c에 추가
		
		JPanel p2 = new JPanel();  //이미지, 상세정보 표시될 p2 패널 생성
		p2.setLayout(null);  //p2의 배치관리자를 null로 설정
		p2.setBounds(300,50,790,470);
		Border moreBorder = BorderFactory.createTitledBorder("상세보기");
		p2.setBorder(moreBorder);
		pImg = new ImgPanel();  //이미지 그려질 ImgPanel 객체 생성
		pImg.setBounds(20,20,150,170);
		p2.add(pImg);   //pImg를 p2에 추가
		pInfo = new InfoPanel();   //상세정보 표시될 pInfo 패널 생성
		pInfo.setBounds(180,20,600,170);
		p2.add(pInfo);  //pInfo를 p2에 추가
		c.add(p2);     //p2를 c에 추가
		
		JPanel p3 = new JPanel();  //줄거리 표시될 p3 패널 생성
		p3.setBounds(5,200,780,130);
		Border summaryBorder = BorderFactory.createTitledBorder("줄거리");
		p3.setBorder(summaryBorder);
		summaryArea = new JTextArea(5,80);  //JTextArea 객체 생성
		p3.add(new JScrollPane(summaryArea)); //summaryArea를 JScrollPane을 통해 p3에 추가
		p2.add(p3);    //p3를 p2에 추가
	
		JPanel p4 = new JPanel();  //감상평 표시될 p4 패널 생성
		p4.setBounds(5,330,780,130);
		Border reviewBorder = BorderFactory.createTitledBorder("감상평");
		p4.setBorder(reviewBorder);
		reviewArea = new JTextArea(5,80);  //JTextArea 객체 생성
		p4.add(new JScrollPane(reviewArea));  //reviewArea를 JScrollPane을 통해 p4에 추가
		p2.add(p4);   //p4를 p2에 추가
		
		JPanel p5 = new JPanel();  //수정 버튼, 삭제 버튼 붙일 p5 패널 생성
		p5.setBounds(550,520,200,100);
		p5.setLayout(new FlowLayout());  //p5의 배치관리자를 FlowLayout으로 설정
		JButton modifyBtn = new JButton("수정"); //수정 버튼 생성
		modifyBtn.addActionListener(new ActionListener() { //수정 버튼에 ActionListener 등록
				public void actionPerformed(ActionEvent e) { //수정 버튼 눌리면
					dialog.btnPressed = 2;  //dialog의 멤버 변수 btnPressed를 2로 변경
					if(selectedItem instanceof Movie) {  //selectedItem이 Movie 타입 이라면
						Movie m = (Movie)selectedItem;  //selectedItem을 Movie 타입으로 다운퀘스팅
						//dialog를 selectedItem의 세부 내용들이 입력된 상태로 설정
						dialog.mv.setSelected(true);
						dialog.mtf1.setText(m.name);
						dialog.mtf2.setText(m.director);
						dialog.mtf3.setText(m.actor);
						dialog.genreCombo.setSelectedItem(m.genre);
						dialog.gradeCombo.setSelectedItem(m.grade);
						dialog.mYearCombo.setSelectedItem(m.year);
						dialog.mtf4.setText(m.imgPath);
						dialog.mSlider.setValue(m.rate);
						dialog.mta1.setText(m.summary);
						dialog.mta2.setText(m.review);
		
						dialog.indexV = ItemCollections.v.indexOf(selectedItem);
						//selectedItem의 v에서의 인덱스를 dialog의 멤버변수 indexV에 저장, dialog에서 수정할수 있게 함
						dialog.indexVm = ItemCollections.vm.indexOf((Movie)selectedItem);
						//selectedItem의 vm에서의 인덱스를 dialog의 멤버변수 indexVm에 저장, dialog에서 수정할수 있게 함
					}
					else if(selectedItem instanceof Book) {  //selectedItem이 Book 타입 이라면
						Book b = (Book)selectedItem;  //selectedItem을 Book 타입으로 다운퀘스팅
						//dialog를 selectedItem의 세부 내용들이 입력된 상태로 설정
						dialog.bk.setSelected(true);
						dialog.btf1.setText(b.name);
						dialog.btf2.setText(b.author);
						dialog.btf3.setText(b.publisher);
						dialog.bYearCombo.setSelectedItem(b.year);
						dialog.btf4.setText(b.imgPath);
						dialog.bSlider.setValue(b.rate);
						dialog.bta1.setText(b.summary);
						dialog.bta2.setText(b.review);
			
						dialog.indexV = ItemCollections.v.indexOf(selectedItem);
						//selectedItem의 v에서의 인덱스를 dialog의 멤버변수 indexV에 저장, dialog에서 수정할수 있게 함
						dialog.indexVb = ItemCollections.vb.indexOf((Book)selectedItem);
						//selectedItem의 vb에서의 인덱스를 dialog의 멤버변수 indexVb에 저장, dialog에서 수정할수 있게 함
					}
					
					dialog.setVisible(true);  //dialog가 보여지게 함
					totalList.setListData(ItemCollections.getVNames()); //totalList에 수정된 것 반영
					movieList.setListData(ItemCollections.getVmNames());  //movieList에 수정된 것 반영
					bookList.setListData(ItemCollections.getVbNames());   //bookList에 수정된 것 반영
					if(selectedItem instanceof Movie) {   //selectedItem이 Movie 타입 이라면
						selectedItem = dialog.movie;    //dialog에서 생성된 객체 movie를 selectedItem으로 설정
						summaryArea.setText(selectedItem.summary);//selectedItem의 summary를 summaryArea에 표시
						reviewArea.setText(selectedItem.review); //selectedItem의 review를 reviewArea에 표시
					}
					else if(selectedItem instanceof Book) { //selectedItem이 Book 타입 이라면
						selectedItem = dialog.book;   //dialog에서 생성된 객체 book을 selectedItem으로 설정
						summaryArea.setText(selectedItem.summary); //selectedItem의 summary를 summaryArea에 표시
						reviewArea.setText(selectedItem.review);  //selectedItem의 review를 reviewArea에 표시
					}
					pImg.repaint(); //pImg 다시 그려서 수정된 내용 반영
					pInfo.repaint();  //pInfo 다시 그려서 수정된 내용 반영
				}
			});
		JButton deleteBtn = new JButton("삭제");  //삭제 버튼 생성
		deleteBtn.addActionListener(new ActionListener() {  //삭제 버튼에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {  //삭제버튼 눌리면
				int answer = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제 확인",
				JOptionPane.YES_NO_OPTION);   //확인 다이얼로그 나타남
				if(answer == JOptionPane.YES_OPTION) {  //사용자가 "예"를 선택한 경우
					ItemCollections.remove(selectedItem);  //selectedItem 삭제
					totalList.setListData(ItemCollections.getVNames());  //totalList에 삭제된 것 반영
					movieList.setListData(ItemCollections.getVmNames()); //movieList에 삭제된 것 반영
					bookList.setListData(ItemCollections.getVbNames());  //movieList에 삭제된 것 반영
					selectedItem = null;    //selectedItem을 null로 설정
					pImg.repaint();   //pImg 다시 그려서 삭제된 것 반영
					pInfo.repaint();  //pInfo 다시 그려서 삭제된 것 반영
					summaryArea.setText("");  //summaryArea 빈칸으로 설정
					reviewArea.setText("");   //reviewArea 빈칸으로 설정
				}
			}
		});
		p5.add(modifyBtn);   p5.add(deleteBtn);  //modifyBtn, deleteBtn을 p5에 추가
		c.add(p5);  //p5을 c에 추가
		
		setSize(1200,800);
		setVisible(true);
	}
	
	public void createMenu() {  //createMenu() 작성
		JMenuBar mb = new JMenuBar();  //JMenuBar 객체 생성
		
		JMenu fileMenu = new JMenu("파일");  //JMenu 객체로 fileMenu 생성
		JMenuItem load = new JMenuItem("불러오기");   //JMenuItem 객체로 load 생성
		load.addActionListener(new ActionListener() {  //load에 ActionListener 등록
			public void actionPerformed(ActionEvent e)  {  //load 눌리면
				JFileChooser chooser = new JFileChooser();  //JFileChooser 객체 생성
				int ret = chooser.showOpenDialog(null);  //열기 다이얼로그 출력
				if(ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();  
					//사용자가 선택한 파일 경로 반환
					try{ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathName));
					//파일로부터 읽어와서 각 벡터에 저장
					ItemCollections.v = (Vector<Item>)ois.readObject();
					ItemCollections.vm = (Vector<Movie>)ois.readObject();
					ItemCollections.vb = (Vector<Book>)ois.readObject();
					ois.close();
					//읽어온 내용 각 리스트에 반영
					totalList.setListData(ItemCollections.getVNames());
					movieList.setListData(ItemCollections.getVmNames());
					bookList.setListData(ItemCollections.getVbNames());
					}
					catch(Exception ex) {return;
					}
				}
			}
		});
		JMenuItem save = new JMenuItem("저장하기");  //JMenuItem 객체로 save 생성
		save.addActionListener(new ActionListener() {  //save에 ActionListener 등록
			public void actionPerformed(ActionEvent e)  {  //save 눌리면
				JFileChooser chooser = new JFileChooser(); //JFileChooser 객체 생성
				int ret = chooser.showSaveDialog(null);  //저장 다이얼로그 출력
				if(ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();  
					//사용자가 선택한 파일 경로 반환
					try{ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathName));
					//파일에 각 벡터 저장
					oos.writeObject(ItemCollections.v);
					oos.writeObject(ItemCollections.vm);
					oos.writeObject(ItemCollections.vb);
					oos.close();
					}
					catch(Exception ex) {return;
					}
				}
			}
		}); 
		JMenuItem exit = new JMenuItem("종료");  //JMenuItem 객체로 exit 생성
		exit.addActionListener(new ActionListener() {  //exit에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {  //exit 눌리면
				System.exit(0);     //프로그램 종료
			}
		});
		fileMenu.add(load);  fileMenu.add(save);  fileMenu.add(exit);  
		
		JMenu helpMenu = new JMenu("도움말");   //JMenu 객체로 helpMenu 생성
		JMenuItem systemInfo = new JMenuItem("시스템 정보");   //JMenuItem 객체로 systemInfo 생성
		systemInfo.addActionListener(new ActionListener() { //systemInfo에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {  //systemInfo 눌리면
				JOptionPane.showMessageDialog(null, "MyNotes 시스템 v 1.0입니다", 
				"Message", JOptionPane.INFORMATION_MESSAGE);  //메시지 다이얼로그 나타남
			}
		});
		helpMenu.add(systemInfo);
		mb.add(fileMenu);   mb.add(helpMenu);
		setJMenuBar(mb);
	}
	
	public JTabbedPane createTabbedPane() {  //createTabbedPane() 작성
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);   //JTabbedPane 객체 생성
		totalList = new JList<String>(ItemCollections.getVNames());  
		//getVNames()가 리턴하는 배열로 JList 객체 생성
		totalList.addListSelectionListener(new ListSelectionListener() { //totalList에 리스너 등록
			public void valueChanged(ListSelectionEvent e) {  
				int selectedIndex = totalList.getSelectedIndex(); 
				//totalList에서 선택된 인덱스를 selectedIndex에 저장
				if(selectedIndex != -1) {  //selectedIndex가 -1이 아니면
					selectedItem = ItemCollections.v.get(selectedIndex);
					//v에서 selectedIndex의 요소를 selectedItem으로 함
					
					//리스트에서 선택된 항목의 상세보기가 나타나도록 함
					pImg.repaint();   
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);	
				}
			}
		});
		movieList = new JList<String>(ItemCollections.getVmNames());
		//getVmNames()가 리턴하는 배열로 JList 객체 생성
		movieList.addListSelectionListener(new ListSelectionListener() {//movieList에 리스너 등록
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = movieList.getSelectedIndex();
				//movieList에서 선택된 인덱스를 selectedIndex에 저장
				if(selectedIndex != -1) {  //selectedIndex가 -1이 아니면
					selectedItem = ItemCollections.vm.get(selectedIndex);
					//vm에서 selectedIndex의 요소를 selectedItem으로 함
					
					//리스트에서 선택된 항목의 상세보기가 나타나도록 함
					pImg.repaint();
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);
				}
			}
		});
		bookList = new JList<String>(ItemCollections.getVbNames());
		//getVbNames()가 리턴하는 배열로 JList 객체 생성
		bookList.addListSelectionListener(new ListSelectionListener() { //bookList에 리스너 등록
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = bookList.getSelectedIndex();
				//bookList에서 선택된 인덱스를 selectedIndex에 저장
				if(selectedIndex != -1) {  //selectedIndex가 -1이 아니면
					selectedItem = ItemCollections.vb.get(selectedIndex);
					//vb에서 selectedIndex의 요소를 selectedItem으로 함
					
					//리스트에서 선택된 항목의 상세보기가 나타나도록 함
					pImg.repaint();
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);	
				}
			}
		});
		JPanel pSearch0 = new JPanel();  //탭팬에 붙일 pSearch0 패널 생성
		pSearch0.setLayout(new BorderLayout());
		JPanel pSearch1 = new JPanel();  //콤보박스, 텍스트필드, 검색버튼 붙일 pSearch1 패널 생성
		String[] searchHow = {"제목", "별점"};
		JComboBox<String> searchCombo = new JComboBox<String>(searchHow); 
		//searchHow 배열로 JComboBox 객체 생성
		pSearch1.add(searchCombo);  //searchCombo를 pSearch1에 추가
		JTextField inputField = new JTextField(15);  //JTextField 객체 생성
		pSearch1.add(inputField);    //inputField를 pSearch1에 추가
		JButton searchBtn = new JButton("검색");  //검색 버튼 생성
		searchList = new JList<String>(result);  //result 배열로 JList 객체 생성
		searchList.addListSelectionListener(new ListSelectionListener() {//searchList에 리스너 등록
			public void valueChanged(ListSelectionEvent e) {
				String selectedValue = searchList.getSelectedValue(); 
				//searchList에서 선택된 문자열을 selectedValue에 저장
				selectedItem = ItemCollections.searchEqualName(selectedValue);
				//searchEqualName() 통해 v에서 selectedValue와 이름 같은 객체를 반환받아 selectedItem으로 함
				if(selectedItem != null) {  //selectedItem이 null이 아니면
					//리스트에서 선택된 항목의 상세보기가 나타나도록 함
					pImg.repaint();
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);	
				}
			}
		});
		searchBtn.addActionListener(new ActionListener() {  //searchBtn에 ActionListener 등록
			public void actionPerformed(ActionEvent e) {  //searchBtn 눌리면
				if(searchCombo.getSelectedIndex() == 0) {  //제목으로 검색할 때는
					result = ItemCollections.searchName(inputField.getText());
					//searchName() 통해 해당 문자열 배열 리턴받음
					if(result[0] == null)  {//배열 원소가 없으면
						JOptionPane.showMessageDialog(null, "["+inputField.getText()+"] 검색 결과가 없습니다", 
						"Message", JOptionPane.INFORMATION_MESSAGE);
						//메시지 다이얼로그 나타남
						searchList.setListData(result);
					}
					else   //배열 원소 있으면
						searchList.setListData(result);  //searchList를 result 배열로 다시 설정
				}
				else if(searchCombo.getSelectedIndex() == 1) {  //별점으로 검색할 때는
					result = ItemCollections.searchRate(Integer.parseInt(inputField.getText()));
					//searchRate() 통해 해당 문자열 배열 리턴받음
					if(result[0] == null) {  //배열 원소가 없으면
						JOptionPane.showMessageDialog(null, "["+inputField.getText()+"] 검색 결과가 없습니다", 
						"Message", JOptionPane.INFORMATION_MESSAGE);
						//메시지 다이얼로그 나타남
						searchList.setListData(result);
					}
					else   //배열 원소 있으면
						searchList.setListData(result);   //searchList를 result 배열로 다시 설정
				}
					
			}
		});
		pSearch1.add(searchBtn);  //searchBtn을 pSearch1에 추가
		pSearch0.add(pSearch1, BorderLayout.NORTH);   //pSearch1을 pSearch0의 NORTH영역에 추가
		pSearch0.add(searchList, BorderLayout.CENTER);  //searchList를 pSearch0의 CENTER영역에 추가
		
		//각 리스트를 탭팬에 추가
		pane.addTab("전체", totalList);
		pane.addTab("영화", movieList);
		pane.addTab("도서", bookList);
		pane.addTab("검색", pSearch0);  //pSearch0를 탭팬에 추가
		return pane;
	}
	
	class ImgPanel extends JPanel{  //JPanel을 상속받는 ImgPanel 생성
		public void paintComponent(Graphics g) {  //paintComponent() 재정의
			super.paintComponent(g); 
			if(selectedItem == null)   //selectedItem이 null이면
				g.drawString("이미지 없음",30,80);   //"이미지 없음" 문자열 그리기
			else {      //selectedItem이 null이 아니면
				ImageIcon icon = new ImageIcon(selectedItem.getImgPath()); 
				//해당 경로로 ImageIcon 객체 생성
				Image img = icon.getImage();   //Image 객체 생성
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
				//ImgPanel에 이미지 그리기
			}
		}
	}
	
	class InfoPanel extends JPanel{  //JPanel 상속받는 InfoPanel 작성
		public void paintComponent(Graphics g) {  //paintComponent() 재정의
			super.paintComponent(g);
			if(selectedItem != null)  //selectedItem이 null이 아니면
				if(selectedItem instanceof Movie) {  //selectedItem이 Movie 타입일 때는
					Movie m = (Movie)selectedItem;  //selectedItem을 Movie 타입으로 다운퀘스팅
					//상세정보 문자열 그리기
					g.drawString("제목",10,10);
					g.drawString("감독",10,35);
					g.drawString("배우",10,60);
					g.drawString("장르",10,85);
					g.drawString("등급",10,110);
					g.drawString("개봉년도",10,135);
					g.drawString("별점",10,160);
					g.drawString(m.name,70,10);
					g.drawString(m.director,70,35);
					g.drawString(m.actor,70,60);
					g.drawString(m.genre,70,85);
					g.drawString(m.grade,70,110);
					g.drawString(m.year,70,135);
					g.drawString(Integer.toString(m.rate),70,160);
				}
				else if(selectedItem instanceof Book) {   //selectedItem이 Book 타입일 때는
					Book b = (Book)selectedItem;    //selectedItem을 Book 타입으로 다운퀘스팅
					//상세정보 문자열 그리기
					g.drawString("제목",10,10);
					g.drawString("저자",10,45);
					g.drawString("출판사",10,80);
					g.drawString("출판년도",10,115);
					g.drawString("별점",10,150);
					g.drawString(b.name,70,10);
					g.drawString(b.author,70,45);
					g.drawString(b.publisher,70,80);
					g.drawString(b.year,70,115);
					g.drawString(Integer.toString(b.rate),70,150);	
				}
		}
	}
	
	public static void main(String[] args) {
		new MyNotes();
	}
}


	
 
