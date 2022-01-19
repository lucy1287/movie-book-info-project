//////////////////////
///������(2016969)
///2021-12-18
///LabFinalProject: ���� ��ȭ/���� ���� ���α׷� �ۼ�
/////////////////////
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import java.util.Vector;
import java.io.*;
import java.util.Calendar;

class Item implements Serializable{   //Item Ŭ���� �ۼ�
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
	public String getImgPath() {  //�̹��� ��� �����ϴ� getImgPath() �ۼ�
		return imgPath;
	}
}

class Movie extends Item implements Serializable{  //Item Ŭ������ ��ӹ޴� Movie Ŭ���� �ۼ�
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

class Book extends Item implements Serializable{  //Item Ŭ������ ��ӹ޴� Book Ŭ���� �ۼ�
	String author, publisher;
	public Book(String name, String author, String publisher, String year, String imgPath, 
	int rate, String summary, String review) {
			super(name, year, imgPath, rate, summary, review);
			this.author = author;
			this.publisher = publisher;
			}
}

class ItemCollections {   //ItemCollections Ŭ���� �ۼ�
	static Vector<Item> v = new Vector<Item>();  //Item Ÿ���� ��ü �����ϴ� ���� v ����
	static Vector<Movie> vm = new Vector<Movie>();  //Movie Ÿ���� ��ü �����ϴ� ���� vm ����
	static Vector<Book> vb = new Vector<Book>();  //Book Ÿ���� ��ü �����ϴ� ���� vb ����
	
	public static String[] getVNames() {//���� v�� ��ҵ��� name��� ������ �迭 �����ϴ� getVNames() �ۼ�
		String[] vNames = new String[v.size()];
		for(int i = 0; i < v.size(); i++)   //v.size()��ŭ �ݺ��� ���鼭
			vNames[i] = v.get(i).name;     //vNames �迭�� �� ����� name ����
		return vNames;
	}
	public static String[] getVmNames() {//���� vm�� ��ҵ��� name��� ������ �迭 �����ϴ� getVmNames() �ۼ�
		String[] vmNames = new String[vm.size()];
		for(int i = 0; i < vm.size(); i++)  //vm.size()��ŭ �ݺ��� ���鼭
			vmNames[i] = vm.get(i).name;    //vmNames �迭�� �� ����� name ����
		return vmNames;
	}
	public static String[] getVbNames() {//���� vb�� ��ҵ��� name��� ������ �迭 �����ϴ� getVbNames() �ۼ�
		String[] vbNames = new String[vb.size()];
		for(int i = 0; i < vb.size(); i++)   //vb.size()��ŭ �ݺ��� ���鼭
			vbNames[i] = vb.get(i).name;     //vbNames �迭�� �� ����� name ����
		return vbNames; 
	}
	public static void add(Item item) {   //Item ��ü�� ���Ϳ� �߰��ϴ� add() �ۼ�
		v.add(item);                   //v�� item �߰�
		if(item instanceof Movie)      //item�� Movie Ÿ���̸�
			vm.add((Movie)item);       //item�� vm�� �߰�
		else if(item instanceof Book)     //item�� Book Ÿ���̸�
			vb.add((Book)item);           //item�� vb�� �߰�
	}
	//Item ��ü�� MovieŸ���� �� �����ϴ� �޼ҵ� modifyMovie() �ۼ�
	public static void modifyMovie(Item item, int indexV, int indexVm) {  
		v.set(indexV, item);            //v���� ����
		vm.set(indexVm, (Movie)item);   //vm���� ����
	}
	//Item ��ü�� BookŸ���� �� �����ϴ� �޼ҵ� modifyBook() �ۼ�
	public static void modifyBook(Item item, int indexV, int indexVb) {
		v.set(indexV, item);           //v���� ����
		vb.set(indexVb, (Book)item);   //vb���� ����
	}
	public static void remove(Item item) {  //Item ��ü�� ���Ϳ��� �����ϴ� remove() �ۼ�
		v.remove(item);              //v���� ����
		if(item instanceof Movie)    //item�� Movie Ÿ���̸�
			vm.remove((Movie)item);  //vm���� ����
		else if(item instanceof Book)    //item�� Book Ÿ���̸�
			vb.remove((Book)item);       //vb���� ����
	}
	public static String[] searchName(String string) {//�̸����� Item ��ü �˻��ϴ� searchName() �ۼ�
		String[] result = new String[v.size()];
		int j = 0;
		for(int i = 0; i < v.size(); i++) {  //v.size()��ŭ �ݺ��� ���鼭
			Item item = v.get(i);           
			if(item.name.contains(string))  //item�� name�� string�� �����ϸ�
				result[j++] = item.name;	//result �迭�� item.name ����			  
		}
		return result;    //result �迭 ����
	}
	public static String[] searchRate(int rate) {//�������� Item ��ü �˻��ϴ� searchRate() �ۼ�
		String[] result = new String[v.size()];
		int j = 0;
		for(int i = 0; i < v.size(); i++) {  //v.size()��ŭ �ݺ��� ���鼭
			Item item = v.get(i);
			if(item.rate >= rate)    //item�� rate�� �Ű������� ���� rate �̻��̸�
				result[j++] = item.name;   //result �迭�� item.name ����	
		}
		return result;   //result �迭 ����
	}
	public static Item searchEqualName(String string) { //�̸� ���� Item ��ü �˻��ϴ� �޼ҵ� �ۼ�
		Item equalItem = null;
		for(int i = 0; i < v.size(); i++) {   //v.size()��ŭ �ݺ��� ���鼭
			Item item = v.get(i);
			if(item.name.equals(string))  //item�� name�� string�� ���ٸ�
				equalItem = item;   //item�� equalItem���� ��
		}
		return equalItem;   //equalItem ����
	}
}

class MyLabel extends JLabel implements Runnable {  //������ ��� �߰��� MyLabel Ŭ���� �ۼ�
	private Thread timerThread = null;
	public MyLabel() {
		setText(makeClockText()); //makeCLockText() ȣ���Ͽ� MyLabel�� �ؽ�Ʈ�� ����
		setFont(new Font("����", Font.ITALIC, 30));
		timerThread = new Thread(MyLabel.this);  //Thread ��ü ����
		timerThread.start();    //������ ���۽�Ŵ
	}
	
	public String makeClockText() {  //makeCLockText() �ۼ�
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		
		String clockText = Integer.toString(year)+"�� "+Integer.toString(month)+"�� "
		+Integer.toString(date)+"�� "+Integer.toString(hour)+":"+Integer.toString(min)
		+":"+Integer.toString(second);   //��,��,��,��,��,�� �̾���� ���ڿ��� clockText�� ����
		return clockText;    //clockText ����
	} 
	public void run() {  //run() ������
		while(true) {
			try {
				Thread.sleep(1000);  //1�� ���� ����
			}
			catch(InterruptedException e){return;}
			setText(makeClockText());   //�ٽ� makeCLockText() ȣ���Ͽ� MyLabel�� �ؽ�Ʈ�� ����
		}
	}
}

class MyDialog extends JDialog {   //JDialog�� ��ӹ޴� MyDialog Ŭ���� �ۼ�
	int btnPressed = 0;
	JRadioButton mv, bk;
	JPanel pMovie, pBook;
	JTextField mtf1, mtf2, mtf3, mtf4;
	JTextArea mta1, mta2;
	JTextField btf1, btf2, btf3, btf4;
	JTextArea bta1, bta2;
	JComboBox genreCombo, gradeCombo, mYearCombo, bYearCombo;
	JSlider mSlider, bSlider;
	String[] genres = {"���", "����", "�׼�", "�θǽ�", "��Ÿ��/SF", "�ִϸ��̼�"};
	String[] grades = {"��ü", "12�� �̻�", "15�� �̻�", "û�ҳ� ���� �Ұ�"};
	String[] years = {"2016","2017","2018","2019","2020","2021"};
	String selectedGenre = genres[0];
	String selectedGrade = grades[0];
	String mSelectedYear = years[0];
	String bSelectedYear = years[0];
	int indexV, indexVm, indexVb;
	Movie movie;
	Book book;
	public MyDialog(JFrame frame, String title) { //������ �ۼ�
		super(frame, title, true);
		JPanel pSelect = new JPanel();  //������ư 2�� ���� pSlect �г� ����
		ButtonGroup group = new ButtonGroup(); //��ư�׷� ��ü ����
		//������ư 2�� ����, ������ MyItemListener ���
		mv = new JRadioButton("Movie", true);   
		mv.addItemListener(new MyItemListener());
		bk = new JRadioButton("Book");
		bk.addItemListener(new MyItemListener());
		group.add(mv);   group.add(bk);
		pSelect.add(mv);  pSelect.add(bk);
		add(pSelect, BorderLayout.NORTH);  //pSelect�� MyDialog�� NORTH������ �߰�
		
		pMovie = new JPanel();   //��ȭ ���� ǥ���� pMovie �г� ����
		pMovie.setLayout(new BorderLayout());
		Border movieBorder = BorderFactory.createTitledBorder("��ȭ ����");
		pMovie.setBorder(movieBorder);
		add(pMovie, BorderLayout.CENTER);  //pMovie�� MyDialog�� CENTER������ �߰�
		
		JPanel pMovie1 = new JPanel();  //��ȭ ������ JLabel���� ���� pMovie1 �г� ���� 
		pMovie1.setLayout(new GridLayout(0,1));  //pMovie1�� ��ġ�����ڸ� GridLayout���� ����
		pMovie1.add(new JLabel("����"));
		pMovie1.add(new JLabel("����"));
		pMovie1.add(new JLabel("���"));
		pMovie1.add(new JLabel("�帣"));
		pMovie1.add(new JLabel("���"));
		pMovie1.add(new JLabel("�����⵵"));
		pMovie1.add(new JLabel("������"));
		pMovie1.add(new JLabel("����"));
		pMovie1.add(new JLabel("�ٰŸ�"));
		pMovie1.add(new JLabel("������"));
		pMovie.add(pMovie1, BorderLayout.WEST); //pMovie1�� pMovie�� WEST������ �߰�
		
		JPanel pMovie2 = new JPanel();  //��ȭ ������ ���� ������Ʈ���� ���� pMovie2 �г� ���� 
		pMovie2.setLayout(new GridLayout(0,1));  //pMovie2�� ��ġ�����ڸ� GridLayout���� ����
		//JTextField ��ü 3�� ����
		mtf1 = new JTextField();
		pMovie2.add(mtf1);
		mtf2 = new JTextField();
		pMovie2.add(mtf2);
		mtf3 = new JTextField();
		pMovie2.add(mtf3);
		genreCombo = new JComboBox<String>(genres);  //genres �迭�� �������� ������ JComboBox ��ü ���� 
		genreCombo.addActionListener(new ActionListener() { //genreCombo�� ActionListener ���
			public void actionPerformed(ActionEvent e) { 
				int genreIndex = genreCombo.getSelectedIndex();//���õ� �������� �ε����� genreIndex�� ����
				selectedGenre = genres[genreIndex]; 
				//genres�迭���� genreIndex�� �ش��ϴ� ���ڿ��� selectGenre�� ����
			}
		});
		pMovie2.add(genreCombo);
		gradeCombo = new JComboBox<String>(grades);  //grades �迭�� �������� ������ JComboBox ��ü ����
		gradeCombo.addActionListener(new ActionListener() {  //gradeCombo�� ActionListener ���
			public void actionPerformed(ActionEvent e) {
				int gradeIndex = gradeCombo.getSelectedIndex();//���õ� �������� �ε����� gradeIndex�� ����
				selectedGrade = grades[gradeIndex];
				//grades�迭���� gradeIndex�� �ش��ϴ� ���ڿ��� selectGrade�� ����
			}
		});
		pMovie2.add(gradeCombo);
		mYearCombo = new JComboBox<String>(years);  //years �迭�� �������� ������ JComboBox ��ü ����
		mYearCombo.addActionListener(new ActionListener() { //mYearCombo�� ActionListener ���
			public void actionPerformed(ActionEvent e) {
				int yearIndex = mYearCombo.getSelectedIndex();//���õ� �������� �ε����� yearIndex�� ����
				mSelectedYear = years[yearIndex];
				//years�迭���� yearIndex�� �ش��ϴ� ���ڿ��� mSelectYear�� ����
			}
		});
		pMovie2.add(mYearCombo);
		
		JPanel pPoster = new JPanel();  //�̹������ �ؽ�Ʈ�ʵ�, �ҷ����� ��ư ���� pPoster �г� ����
		mtf4 = new JTextField(20);   //�̹������ ǥ���� JTextField ����
		pPoster.add(mtf4);
		JButton loadBtn = new JButton("�ҷ�����");  //�ҷ����� ��ư ����
		loadBtn.addActionListener(new ActionListener() {  //��ư�� ActionListener ���
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();  //JFileChooser ��ü ����
				int ret = chooser.showOpenDialog(null);  //���� ���̾�α� ���
				if(ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();  
					//����ڰ� ������ ���� ��� ��ȯ
					mtf4.setText(pathName);  //pathName�� �̹������ �ؽ�Ʈ�ʵ忡 ǥ��
				}
			}
		});
		pPoster.add(loadBtn);
		pMovie2.add(pPoster);  //pPoster�� pMovie2�� �߰�
		
		mSlider = new JSlider(JSlider.HORIZONTAL,1,10,5);  //JSlider ��ü ����
		mSlider.setPaintLabels(true);
		mSlider.setPaintTicks(true);
		mSlider.setPaintTrack(true);
		mSlider.setMajorTickSpacing(1);
		pMovie2.add(mSlider);  //mSlider�� pMovie2�� �߰�
		
		mta1 = new JTextArea();  //�ٰŸ� �ۼ� ���� JTextArea ��ü ����
		pMovie2.add(new JScrollPane(mta1));  //mta1�� JScrollPane�� ���ؼ� pMovie2�� �߰�
		mta2 = new JTextArea();  //������ �ۼ� ���� JTextArea ��ü ����
		pMovie2.add(new JScrollPane(mta2));  //mta2�� JScrollPane�� ���ؼ� pMovie2�� �߰�
		JPanel pOk = new JPanel();   //OK ��ư ���̱� ���� pOK �г� ����
		JButton okBtn = new JButton("OK");  //OK ��ư ����
		okBtn.addActionListener(new ActionListener() {  //okBtn�� ActionListener ���
			public void actionPerformed(ActionEvent e) {
				//�� ������Ʈ�鿡 �Էµ� ������ ������
				String name = mtf1.getText();
				String director = mtf2.getText();
				String actor = mtf3.getText();
				String imgPath = mtf4.getText();
				int rate = mSlider.getValue();
				String summery = mta1.getText();
				String review = mta2.getText();
				//������ ������ Movie ��ü ����
				movie = new Movie(name, director, actor, selectedGenre,
				selectedGrade, mSelectedYear, imgPath, rate, summery, review);
				if(btnPressed == 1)  //���� �߰� ��ư�� ���ȴٸ�
					ItemCollections.add(movie);	  //movie�� add() ���� ���Ϳ� �߰�
				else if(btnPressed == 2)   //���� ���� ��ư�� ���ȴٸ�
					ItemCollections.modifyMovie(movie, indexV, indexVm);
				    //movie�� modifyMovie() ���� ����
				//�ٽ� MyDialog �ʱ�ȭ
				mv.setSelected(true);
				mtf1.setText(""); mtf2.setText(""); mtf3.setText(""); mtf4.setText("");
				mta1.setText(""); mta2.setText("");
				genreCombo.setSelectedIndex(0);
				gradeCombo.setSelectedIndex(0);
				mYearCombo.setSelectedIndex(0);
				mSlider.setValue(5);
				setVisible(false);  //MyDialog ������ �ʰ� ��
				btnPressed = 0;   //btnPressed �ٽ� 0���� ����
			}
		});	
		pOk.add(okBtn);
		pMovie.add(pMovie2, BorderLayout.CENTER);  //pMovie2�� pMovie�� CENTER������ �߰�
		pMovie.add(pOk, BorderLayout.SOUTH); //pOk�� pMovie�� SOUTH������ �߰�
	
		setSize(400, 600);
	}
	
	private class MyItemListener implements ItemListener{  //MyItemListener Ŭ���� �ۼ�
		public void itemStateChanged(ItemEvent e) {  //���� ��ư�� ������ ����ȴٸ�
			if(mv.isSelected()) {  //mv�� ���õǾ��� ��
				pMovie.setVisible(true);  //pMovie�� �������� ��
				pBook.setVisible(false);  //pBook�� �������� �ʰ� ��
			}
			else if(bk.isSelected()) {  //bk�� ���õǾ��� ��
				pBook = new JPanel();  //���� ���� ǥ���� pBook �г� ����
				pBook.setLayout(new BorderLayout()); 
				Border bookBorder = BorderFactory.createTitledBorder("���� ����");
				pBook.setBorder(bookBorder);
				add(pBook, BorderLayout.CENTER); //pBook�� MyDialog�� CENTER������ �߰�
					
				JPanel pBook1 = new JPanel(); //���� ������ JLabel���� ���� pBook1 �г� ���� 
				pBook1.setLayout(new GridLayout(0,1)); //pBook1�� ��ġ�����ڸ� GridLayout���� ����
				pBook1.add(new JLabel("����"));
				pBook1.add(new JLabel("����"));
				pBook1.add(new JLabel("���ǻ�"));
				pBook1.add(new JLabel("���ǳ⵵"));
				pBook1.add(new JLabel("å�̹���"));
				pBook1.add(new JLabel("����"));
				pBook1.add(new JLabel("å�Ұ�"));
				pBook1.add(new JLabel("������"));
				pBook.add(pBook1, BorderLayout.WEST); //pBook1�� pBook�� WEST������ �߰�
				
				JPanel pBook2 = new JPanel(); //���� ������ ���� ������Ʈ���� ���� pBook2 �г� ���� 
				pBook2.setLayout(new GridLayout(0,1)); //pBook2�� ��ġ�����ڸ� GridLayout���� ����
				//JTextField 3�� ����
				btf1 = new JTextField();
				pBook2.add(btf1);
				btf2 = new JTextField();
				pBook2.add(btf2);
				btf3 = new JTextField();
				pBook2.add(btf3);
				bYearCombo = new JComboBox<String>(years);//years �迭�� �������� ������ JComboBox ��ü ����
				bYearCombo.addActionListener(new ActionListener() { //bYearCombo�� ActionListener ���
					public void actionPerformed(ActionEvent e) {
						int yearIndex = bYearCombo.getSelectedIndex();//���õ� �������� �ε����� yearIndex�� ����
						bSelectedYear = years[yearIndex];
						//years�迭���� yearIndex�� �ش��ϴ� ���ڿ��� bSelectYear�� ����
					}
				});
				pBook2.add(bYearCombo);
				JPanel pPoster = new JPanel();  //�̹������ �ؽ�Ʈ�ʵ�, �ҷ����� ��ư ���� pPoster �г� ����
				btf4 = new JTextField(20);  //�̹������ ǥ���� JTextField ����
				pPoster.add(btf4);
				JButton loadBtn = new JButton("�ҷ�����"); //�ҷ����� ��ư ����
				loadBtn.addActionListener(new ActionListener() {  //��ư�� ActionListener ���
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser();  //JFileChooser ��ü ����
						int ret = chooser.showOpenDialog(null);  //���� ���̾�α� ���
						if(ret == JFileChooser.APPROVE_OPTION) {
							String pathName = chooser.getSelectedFile().getPath();  
							//����ڰ� ������ ���� ��� ��ȯ
							btf4.setText(pathName);  //pathName�� �̹������ �ؽ�Ʈ�ʵ忡 ǥ��
						}
					}
				});
				pPoster.add(loadBtn);
				pBook2.add(pPoster); //pPoster�� pBook2�� �߰�
				
				bSlider = new JSlider(JSlider.HORIZONTAL,1,10,5);  //JSLider ��ü ����
				bSlider.setPaintLabels(true);
				bSlider.setPaintTicks(true);
				bSlider.setPaintTrack(true);
				bSlider.setMajorTickSpacing(1);
				pBook2.add(bSlider);  //mSlider�� pBook2�� �߰�
				
				bta1 = new JTextArea();  //�ٰŸ� �ۼ� ���� JTextArea ��ü ����
				pBook2.add(new JScrollPane(bta1));  //bta1�� JScrollPane�� ���ؼ� pMovie2�� �߰�
				bta2 = new JTextArea();  //������ �ۼ� ���� JTextArea ��ü ����
				pBook2.add(new JScrollPane(bta2));   //bta2�� JScrollPane�� ���ؼ� pMovie2�� �߰�
				JPanel pOk = new JPanel();  //OK ��ư ���̱� ���� pOK �г� ����
				JButton okBtn = new JButton("OK");  //OK ��ư ����
				okBtn.addActionListener(new ActionListener() {  //okBtn�� ActionListener ���
					public void actionPerformed(ActionEvent e) {
						//�� ������Ʈ�鿡 �Էµ� ������ ������
						String name = btf1.getText();
						String author = btf2.getText();
						String publisher = btf3.getText();
						String imgPath = btf4.getText();
						int rate = bSlider.getValue();
						String summery = bta1.getText();
						String review = bta2.getText();
						//������ ������ Movie ��ü ����
						book = new Book(name, author, publisher, bSelectedYear, 
								imgPath, rate, summery, review);
						if(btnPressed == 1)   //���� �߰� ��ư�� ���ȴٸ�
							ItemCollections.add(book);	 //book�� add() ���� ���Ϳ� �߰�
						else if(btnPressed == 2)  //���� ���� ��ư�� ���ȴٸ�
							ItemCollections.modifyBook(book, indexV, indexVb);
							//book�� modifyMovie() ���� ����	
						//�ٽ� MyDialog �ʱ�ȭ
						mv.setSelected(true);
						btf1.setText(""); btf2.setText(""); btf3.setText(""); btf4.setText("");
						bta1.setText(""); bta2.setText("");
						bYearCombo.setSelectedIndex(0); 
						bSlider.setValue(5);
						setVisible(false);  //MyDialog ������ �ʰ� ��
						btnPressed = 0;   //btnPressed �ٽ� 0���� ����
					}
				});	
				pOk.add(okBtn);
				pBook.add(pBook2, BorderLayout.CENTER);  //p2�� pBook�� CENTER������ �߰�
				pBook.add(pOk, BorderLayout.SOUTH);   //pOk�� pBook�� SOUTH������ �߰�
				
				pMovie.setVisible(false);  //pMovie�� �������� �ʰ� ��
				pBook.setVisible(true);    //pBook�� �������� ��
			}
		}
	}
}

public class MyNotes extends JFrame{  //JFrame�� ��ӹ޴� MyNotes Ŭ���� �ۼ�
	JList<String> totalList, movieList, bookList, searchList;
	String[] result = {};
	JTextArea summaryArea, reviewArea;
	Item selectedItem;
	ImgPanel pImg;
	JPanel pInfo;
	public MyNotes() {  //������ �ۼ�
		setTitle("JAVA 4�й� 2016969 ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();   //createMenu() ȣ��
		Container c = getContentPane();
		c.setLayout(null);  //c�� ��ġ�����ڸ� null�� ����
		
		JPanel p0 = new JPanel();  //"My Notes" ǥ�õǴ� ��, ����ð� ǥ�õǴ� �� ���� p0 �г� ����
		p0.setLayout(null);  //p0�� ��ġ�����ڸ� null�� ����
		p0.setBounds(0,0,1200,50);
		JLabel lbl = new JLabel("My Notes");  //"My Notes" ǥ�õ� JLabel ��ü ����
		lbl.setBounds(100,0,200,30);
		lbl.setFont(new Font("����", Font.ITALIC+Font.BOLD, 30));
		lbl.setForeground(Color.BLUE);
		lbl.setOpaque(true);
		p0.add(lbl);   //p0�� lbl �߰�
		MyLabel lblTime = new MyLabel();  //���� �ð� ǥ�õ� MyLabel ��ü ����
		lblTime.setBounds(850,0,500,30);
		lblTime.setFont(new Font("����", Font.ITALIC, 18));
		p0.add(lblTime);  //p0�� lblTime �߰�
		c.add(p0);    //c�� p0 �߰�
		
		JPanel p1 = new JPanel();  //����, �߰� ��ư ���� p1 �г� ����
		p1.setLayout(null);   //p1�� ��ġ�����ڸ� null�� ����
		p1.setBounds(0,50,300,600);
		JTabbedPane pane = createTabbedPane();  //createTabbedPane() ȣ��
		pane.setBounds(0,0,300,470);
		p1.add(pane);   //p1�� pane �߰�
		MyDialog dialog = new MyDialog(this, "�Է�");  //MyDialog ��ü ����
		JButton addBtn = new JButton("�߰�");  //�߰� ��ư ����
		addBtn.addActionListener(new ActionListener() {  //addBtn�� ActionListener ���
			public void actionPerformed(ActionEvent e) {  //addBtn ������
				dialog.btnPressed = 1;  //dialog�� ������� btnPressed�� 1�� ����
				dialog.setVisible(true);  //dialog�� ǥ�õǵ��� ��
				totalList.setListData(ItemCollections.getVNames());  //totalList�� �߰��� �� �ݿ�
				movieList.setListData(ItemCollections.getVmNames()); //movieList�� �߰��� �� �ݿ�
				bookList.setListData(ItemCollections.getVbNames());  //bookList�� �߰��� �� �ݿ�
			}
		});
		addBtn.setBounds(120,480,70,30);
		p1.add(addBtn);  //addBtn�� p1�� �߰�
		c.add(p1);  //p1�� c�� �߰�
		
		JPanel p2 = new JPanel();  //�̹���, ������ ǥ�õ� p2 �г� ����
		p2.setLayout(null);  //p2�� ��ġ�����ڸ� null�� ����
		p2.setBounds(300,50,790,470);
		Border moreBorder = BorderFactory.createTitledBorder("�󼼺���");
		p2.setBorder(moreBorder);
		pImg = new ImgPanel();  //�̹��� �׷��� ImgPanel ��ü ����
		pImg.setBounds(20,20,150,170);
		p2.add(pImg);   //pImg�� p2�� �߰�
		pInfo = new InfoPanel();   //������ ǥ�õ� pInfo �г� ����
		pInfo.setBounds(180,20,600,170);
		p2.add(pInfo);  //pInfo�� p2�� �߰�
		c.add(p2);     //p2�� c�� �߰�
		
		JPanel p3 = new JPanel();  //�ٰŸ� ǥ�õ� p3 �г� ����
		p3.setBounds(5,200,780,130);
		Border summaryBorder = BorderFactory.createTitledBorder("�ٰŸ�");
		p3.setBorder(summaryBorder);
		summaryArea = new JTextArea(5,80);  //JTextArea ��ü ����
		p3.add(new JScrollPane(summaryArea)); //summaryArea�� JScrollPane�� ���� p3�� �߰�
		p2.add(p3);    //p3�� p2�� �߰�
	
		JPanel p4 = new JPanel();  //������ ǥ�õ� p4 �г� ����
		p4.setBounds(5,330,780,130);
		Border reviewBorder = BorderFactory.createTitledBorder("������");
		p4.setBorder(reviewBorder);
		reviewArea = new JTextArea(5,80);  //JTextArea ��ü ����
		p4.add(new JScrollPane(reviewArea));  //reviewArea�� JScrollPane�� ���� p4�� �߰�
		p2.add(p4);   //p4�� p2�� �߰�
		
		JPanel p5 = new JPanel();  //���� ��ư, ���� ��ư ���� p5 �г� ����
		p5.setBounds(550,520,200,100);
		p5.setLayout(new FlowLayout());  //p5�� ��ġ�����ڸ� FlowLayout���� ����
		JButton modifyBtn = new JButton("����"); //���� ��ư ����
		modifyBtn.addActionListener(new ActionListener() { //���� ��ư�� ActionListener ���
				public void actionPerformed(ActionEvent e) { //���� ��ư ������
					dialog.btnPressed = 2;  //dialog�� ��� ���� btnPressed�� 2�� ����
					if(selectedItem instanceof Movie) {  //selectedItem�� Movie Ÿ�� �̶��
						Movie m = (Movie)selectedItem;  //selectedItem�� Movie Ÿ������ �ٿ�������
						//dialog�� selectedItem�� ���� ������� �Էµ� ���·� ����
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
						//selectedItem�� v������ �ε����� dialog�� ������� indexV�� ����, dialog���� �����Ҽ� �ְ� ��
						dialog.indexVm = ItemCollections.vm.indexOf((Movie)selectedItem);
						//selectedItem�� vm������ �ε����� dialog�� ������� indexVm�� ����, dialog���� �����Ҽ� �ְ� ��
					}
					else if(selectedItem instanceof Book) {  //selectedItem�� Book Ÿ�� �̶��
						Book b = (Book)selectedItem;  //selectedItem�� Book Ÿ������ �ٿ�������
						//dialog�� selectedItem�� ���� ������� �Էµ� ���·� ����
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
						//selectedItem�� v������ �ε����� dialog�� ������� indexV�� ����, dialog���� �����Ҽ� �ְ� ��
						dialog.indexVb = ItemCollections.vb.indexOf((Book)selectedItem);
						//selectedItem�� vb������ �ε����� dialog�� ������� indexVb�� ����, dialog���� �����Ҽ� �ְ� ��
					}
					
					dialog.setVisible(true);  //dialog�� �������� ��
					totalList.setListData(ItemCollections.getVNames()); //totalList�� ������ �� �ݿ�
					movieList.setListData(ItemCollections.getVmNames());  //movieList�� ������ �� �ݿ�
					bookList.setListData(ItemCollections.getVbNames());   //bookList�� ������ �� �ݿ�
					if(selectedItem instanceof Movie) {   //selectedItem�� Movie Ÿ�� �̶��
						selectedItem = dialog.movie;    //dialog���� ������ ��ü movie�� selectedItem���� ����
						summaryArea.setText(selectedItem.summary);//selectedItem�� summary�� summaryArea�� ǥ��
						reviewArea.setText(selectedItem.review); //selectedItem�� review�� reviewArea�� ǥ��
					}
					else if(selectedItem instanceof Book) { //selectedItem�� Book Ÿ�� �̶��
						selectedItem = dialog.book;   //dialog���� ������ ��ü book�� selectedItem���� ����
						summaryArea.setText(selectedItem.summary); //selectedItem�� summary�� summaryArea�� ǥ��
						reviewArea.setText(selectedItem.review);  //selectedItem�� review�� reviewArea�� ǥ��
					}
					pImg.repaint(); //pImg �ٽ� �׷��� ������ ���� �ݿ�
					pInfo.repaint();  //pInfo �ٽ� �׷��� ������ ���� �ݿ�
				}
			});
		JButton deleteBtn = new JButton("����");  //���� ��ư ����
		deleteBtn.addActionListener(new ActionListener() {  //���� ��ư�� ActionListener ���
			public void actionPerformed(ActionEvent e) {  //������ư ������
				int answer = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "���� Ȯ��",
				JOptionPane.YES_NO_OPTION);   //Ȯ�� ���̾�α� ��Ÿ��
				if(answer == JOptionPane.YES_OPTION) {  //����ڰ� "��"�� ������ ���
					ItemCollections.remove(selectedItem);  //selectedItem ����
					totalList.setListData(ItemCollections.getVNames());  //totalList�� ������ �� �ݿ�
					movieList.setListData(ItemCollections.getVmNames()); //movieList�� ������ �� �ݿ�
					bookList.setListData(ItemCollections.getVbNames());  //movieList�� ������ �� �ݿ�
					selectedItem = null;    //selectedItem�� null�� ����
					pImg.repaint();   //pImg �ٽ� �׷��� ������ �� �ݿ�
					pInfo.repaint();  //pInfo �ٽ� �׷��� ������ �� �ݿ�
					summaryArea.setText("");  //summaryArea ��ĭ���� ����
					reviewArea.setText("");   //reviewArea ��ĭ���� ����
				}
			}
		});
		p5.add(modifyBtn);   p5.add(deleteBtn);  //modifyBtn, deleteBtn�� p5�� �߰�
		c.add(p5);  //p5�� c�� �߰�
		
		setSize(1200,800);
		setVisible(true);
	}
	
	public void createMenu() {  //createMenu() �ۼ�
		JMenuBar mb = new JMenuBar();  //JMenuBar ��ü ����
		
		JMenu fileMenu = new JMenu("����");  //JMenu ��ü�� fileMenu ����
		JMenuItem load = new JMenuItem("�ҷ�����");   //JMenuItem ��ü�� load ����
		load.addActionListener(new ActionListener() {  //load�� ActionListener ���
			public void actionPerformed(ActionEvent e)  {  //load ������
				JFileChooser chooser = new JFileChooser();  //JFileChooser ��ü ����
				int ret = chooser.showOpenDialog(null);  //���� ���̾�α� ���
				if(ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();  
					//����ڰ� ������ ���� ��� ��ȯ
					try{ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathName));
					//���Ϸκ��� �о�ͼ� �� ���Ϳ� ����
					ItemCollections.v = (Vector<Item>)ois.readObject();
					ItemCollections.vm = (Vector<Movie>)ois.readObject();
					ItemCollections.vb = (Vector<Book>)ois.readObject();
					ois.close();
					//�о�� ���� �� ����Ʈ�� �ݿ�
					totalList.setListData(ItemCollections.getVNames());
					movieList.setListData(ItemCollections.getVmNames());
					bookList.setListData(ItemCollections.getVbNames());
					}
					catch(Exception ex) {return;
					}
				}
			}
		});
		JMenuItem save = new JMenuItem("�����ϱ�");  //JMenuItem ��ü�� save ����
		save.addActionListener(new ActionListener() {  //save�� ActionListener ���
			public void actionPerformed(ActionEvent e)  {  //save ������
				JFileChooser chooser = new JFileChooser(); //JFileChooser ��ü ����
				int ret = chooser.showSaveDialog(null);  //���� ���̾�α� ���
				if(ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();  
					//����ڰ� ������ ���� ��� ��ȯ
					try{ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathName));
					//���Ͽ� �� ���� ����
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
		JMenuItem exit = new JMenuItem("����");  //JMenuItem ��ü�� exit ����
		exit.addActionListener(new ActionListener() {  //exit�� ActionListener ���
			public void actionPerformed(ActionEvent e) {  //exit ������
				System.exit(0);     //���α׷� ����
			}
		});
		fileMenu.add(load);  fileMenu.add(save);  fileMenu.add(exit);  
		
		JMenu helpMenu = new JMenu("����");   //JMenu ��ü�� helpMenu ����
		JMenuItem systemInfo = new JMenuItem("�ý��� ����");   //JMenuItem ��ü�� systemInfo ����
		systemInfo.addActionListener(new ActionListener() { //systemInfo�� ActionListener ���
			public void actionPerformed(ActionEvent e) {  //systemInfo ������
				JOptionPane.showMessageDialog(null, "MyNotes �ý��� v 1.0�Դϴ�", 
				"Message", JOptionPane.INFORMATION_MESSAGE);  //�޽��� ���̾�α� ��Ÿ��
			}
		});
		helpMenu.add(systemInfo);
		mb.add(fileMenu);   mb.add(helpMenu);
		setJMenuBar(mb);
	}
	
	public JTabbedPane createTabbedPane() {  //createTabbedPane() �ۼ�
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);   //JTabbedPane ��ü ����
		totalList = new JList<String>(ItemCollections.getVNames());  
		//getVNames()�� �����ϴ� �迭�� JList ��ü ����
		totalList.addListSelectionListener(new ListSelectionListener() { //totalList�� ������ ���
			public void valueChanged(ListSelectionEvent e) {  
				int selectedIndex = totalList.getSelectedIndex(); 
				//totalList���� ���õ� �ε����� selectedIndex�� ����
				if(selectedIndex != -1) {  //selectedIndex�� -1�� �ƴϸ�
					selectedItem = ItemCollections.v.get(selectedIndex);
					//v���� selectedIndex�� ��Ҹ� selectedItem���� ��
					
					//����Ʈ���� ���õ� �׸��� �󼼺��Ⱑ ��Ÿ������ ��
					pImg.repaint();   
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);	
				}
			}
		});
		movieList = new JList<String>(ItemCollections.getVmNames());
		//getVmNames()�� �����ϴ� �迭�� JList ��ü ����
		movieList.addListSelectionListener(new ListSelectionListener() {//movieList�� ������ ���
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = movieList.getSelectedIndex();
				//movieList���� ���õ� �ε����� selectedIndex�� ����
				if(selectedIndex != -1) {  //selectedIndex�� -1�� �ƴϸ�
					selectedItem = ItemCollections.vm.get(selectedIndex);
					//vm���� selectedIndex�� ��Ҹ� selectedItem���� ��
					
					//����Ʈ���� ���õ� �׸��� �󼼺��Ⱑ ��Ÿ������ ��
					pImg.repaint();
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);
				}
			}
		});
		bookList = new JList<String>(ItemCollections.getVbNames());
		//getVbNames()�� �����ϴ� �迭�� JList ��ü ����
		bookList.addListSelectionListener(new ListSelectionListener() { //bookList�� ������ ���
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = bookList.getSelectedIndex();
				//bookList���� ���õ� �ε����� selectedIndex�� ����
				if(selectedIndex != -1) {  //selectedIndex�� -1�� �ƴϸ�
					selectedItem = ItemCollections.vb.get(selectedIndex);
					//vb���� selectedIndex�� ��Ҹ� selectedItem���� ��
					
					//����Ʈ���� ���õ� �׸��� �󼼺��Ⱑ ��Ÿ������ ��
					pImg.repaint();
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);	
				}
			}
		});
		JPanel pSearch0 = new JPanel();  //���ҿ� ���� pSearch0 �г� ����
		pSearch0.setLayout(new BorderLayout());
		JPanel pSearch1 = new JPanel();  //�޺��ڽ�, �ؽ�Ʈ�ʵ�, �˻���ư ���� pSearch1 �г� ����
		String[] searchHow = {"����", "����"};
		JComboBox<String> searchCombo = new JComboBox<String>(searchHow); 
		//searchHow �迭�� JComboBox ��ü ����
		pSearch1.add(searchCombo);  //searchCombo�� pSearch1�� �߰�
		JTextField inputField = new JTextField(15);  //JTextField ��ü ����
		pSearch1.add(inputField);    //inputField�� pSearch1�� �߰�
		JButton searchBtn = new JButton("�˻�");  //�˻� ��ư ����
		searchList = new JList<String>(result);  //result �迭�� JList ��ü ����
		searchList.addListSelectionListener(new ListSelectionListener() {//searchList�� ������ ���
			public void valueChanged(ListSelectionEvent e) {
				String selectedValue = searchList.getSelectedValue(); 
				//searchList���� ���õ� ���ڿ��� selectedValue�� ����
				selectedItem = ItemCollections.searchEqualName(selectedValue);
				//searchEqualName() ���� v���� selectedValue�� �̸� ���� ��ü�� ��ȯ�޾� selectedItem���� ��
				if(selectedItem != null) {  //selectedItem�� null�� �ƴϸ�
					//����Ʈ���� ���õ� �׸��� �󼼺��Ⱑ ��Ÿ������ ��
					pImg.repaint();
					pInfo.repaint();
					summaryArea.setText(selectedItem.summary);
					reviewArea.setText(selectedItem.review);	
				}
			}
		});
		searchBtn.addActionListener(new ActionListener() {  //searchBtn�� ActionListener ���
			public void actionPerformed(ActionEvent e) {  //searchBtn ������
				if(searchCombo.getSelectedIndex() == 0) {  //�������� �˻��� ����
					result = ItemCollections.searchName(inputField.getText());
					//searchName() ���� �ش� ���ڿ� �迭 ���Ϲ���
					if(result[0] == null)  {//�迭 ���Ұ� ������
						JOptionPane.showMessageDialog(null, "["+inputField.getText()+"] �˻� ����� �����ϴ�", 
						"Message", JOptionPane.INFORMATION_MESSAGE);
						//�޽��� ���̾�α� ��Ÿ��
						searchList.setListData(result);
					}
					else   //�迭 ���� ������
						searchList.setListData(result);  //searchList�� result �迭�� �ٽ� ����
				}
				else if(searchCombo.getSelectedIndex() == 1) {  //�������� �˻��� ����
					result = ItemCollections.searchRate(Integer.parseInt(inputField.getText()));
					//searchRate() ���� �ش� ���ڿ� �迭 ���Ϲ���
					if(result[0] == null) {  //�迭 ���Ұ� ������
						JOptionPane.showMessageDialog(null, "["+inputField.getText()+"] �˻� ����� �����ϴ�", 
						"Message", JOptionPane.INFORMATION_MESSAGE);
						//�޽��� ���̾�α� ��Ÿ��
						searchList.setListData(result);
					}
					else   //�迭 ���� ������
						searchList.setListData(result);   //searchList�� result �迭�� �ٽ� ����
				}
					
			}
		});
		pSearch1.add(searchBtn);  //searchBtn�� pSearch1�� �߰�
		pSearch0.add(pSearch1, BorderLayout.NORTH);   //pSearch1�� pSearch0�� NORTH������ �߰�
		pSearch0.add(searchList, BorderLayout.CENTER);  //searchList�� pSearch0�� CENTER������ �߰�
		
		//�� ����Ʈ�� ���ҿ� �߰�
		pane.addTab("��ü", totalList);
		pane.addTab("��ȭ", movieList);
		pane.addTab("����", bookList);
		pane.addTab("�˻�", pSearch0);  //pSearch0�� ���ҿ� �߰�
		return pane;
	}
	
	class ImgPanel extends JPanel{  //JPanel�� ��ӹ޴� ImgPanel ����
		public void paintComponent(Graphics g) {  //paintComponent() ������
			super.paintComponent(g); 
			if(selectedItem == null)   //selectedItem�� null�̸�
				g.drawString("�̹��� ����",30,80);   //"�̹��� ����" ���ڿ� �׸���
			else {      //selectedItem�� null�� �ƴϸ�
				ImageIcon icon = new ImageIcon(selectedItem.getImgPath()); 
				//�ش� ��η� ImageIcon ��ü ����
				Image img = icon.getImage();   //Image ��ü ����
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
				//ImgPanel�� �̹��� �׸���
			}
		}
	}
	
	class InfoPanel extends JPanel{  //JPanel ��ӹ޴� InfoPanel �ۼ�
		public void paintComponent(Graphics g) {  //paintComponent() ������
			super.paintComponent(g);
			if(selectedItem != null)  //selectedItem�� null�� �ƴϸ�
				if(selectedItem instanceof Movie) {  //selectedItem�� Movie Ÿ���� ����
					Movie m = (Movie)selectedItem;  //selectedItem�� Movie Ÿ������ �ٿ�������
					//������ ���ڿ� �׸���
					g.drawString("����",10,10);
					g.drawString("����",10,35);
					g.drawString("���",10,60);
					g.drawString("�帣",10,85);
					g.drawString("���",10,110);
					g.drawString("�����⵵",10,135);
					g.drawString("����",10,160);
					g.drawString(m.name,70,10);
					g.drawString(m.director,70,35);
					g.drawString(m.actor,70,60);
					g.drawString(m.genre,70,85);
					g.drawString(m.grade,70,110);
					g.drawString(m.year,70,135);
					g.drawString(Integer.toString(m.rate),70,160);
				}
				else if(selectedItem instanceof Book) {   //selectedItem�� Book Ÿ���� ����
					Book b = (Book)selectedItem;    //selectedItem�� Book Ÿ������ �ٿ�������
					//������ ���ڿ� �׸���
					g.drawString("����",10,10);
					g.drawString("����",10,45);
					g.drawString("���ǻ�",10,80);
					g.drawString("���ǳ⵵",10,115);
					g.drawString("����",10,150);
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


	
 
