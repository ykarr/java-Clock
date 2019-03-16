package clock01;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SClock extends JFrame {

	//shutdown 
	private int time=0; //shutdown 시간
	String word=null; //shutdown 글씨
	private JTextField shutdowntime = new JTextField("시간(초)입력 ex)300-> 5분후 꺼짐");//셧다운 명령어에 사용할 시간을 받는 텍스트필드
	private JTextField shutdownstring = new JTextField("아무 문자나 입력(입력하지 않아도 됨)");//셧다운 명령어에 사용할 문자를 받는 텍스트 필드
	private JButton shutdownSet = new JButton("Set");//입력한 정보로 셧다운을 실행하는 버튼
	private JButton shutdownunSet = new JButton("UnSet");//입력한 정보로 셧다운을 실행하는 버튼
	private JButton shutdown = new JButton();//셧다운과 관련된것을 보이게하고 나머지는 보이지 않게 해주는 버튼
	//Alarm
	private int Alarm1,Alarm2,Alarm3; //알람에 필요한 정보들을 정수형으로 받음
	private int count=0; //알람에서 사용하며 알람기능이 while문에 포함되어있어 알람의 소리가 여러번실행되지 않도록 하기위해서 선언
	private JTextField a1 = new JTextField("오전=0 오후=1");//알람에 필요한 정보를 입력받아 다른 변수에 전달
	private JTextField a2 = new JTextField("시간을 입력하시오");//알람에 필요한 정보를 입력받아 다른 변수에 전달
	private JTextField a3 = new JTextField("분을 입력하시오");//알람에 필요한 정보를 입력받아 다른 변수에 전달
	private JButton Alarm = new JButton(); //알람에 관련된것을 보이게하고 나머지는 보이지 않게 해주는 버튼
	private JButton Set = new JButton("Set");//입력된 정보로 알람을 설정하는 버튼
	//Clock
	private JLabel Lstopwatch = new JLabel();//1초마다 1씩오르는 스톱워치의 숫자를 표시해준다.
	private JLabel first = new JLabel(); //년,월,일을 표시해준다.
	private JLabel second = new JLabel(); //오전/오후, 시,분,초를 표시해준다.
	private JLabel Alarmlabel=new JLabel();//
	private String one;//년,월,일을 문자열로 만든다 
	private String two;//오전/오후, 시, 분, 초를 문자열로 만든다.
	private int mouseX, mouseY; //x랑 y의 위치를 저장
	private JButton exitbutton = new JButton(new ImageIcon(Main.class.getResource("../images/exitButtonU.png")));//상단메뉴의 나가기버튼
	//스탑워치
	private int i=0; //스톱워치에서 사용하며 0일때는 스톱워치가 동작하지 않고 1일때 동작한다.
	int stop=0; //스톱워치에 사용할 변수  msec(1자리)
	int ssec=0;//스톱워치에 사용할 변수  초
	int smin=0;//스톱워치에 사용할 변수  분
	int shour=0;//스톱워치에 사용할 변수 시간
	private JButton stopstart = new JButton();//스톱워치 시작버튼
	private JButton swstop = new JButton();//스톱워치 중지버튼
	private JButton stopwatch = new JButton();//스탑워치와 관련된것들을 보이게만들고 나머지는 보이지 않게 만든다.
	private JButton stopreset = new JButton("reset");//스탑워치 리셋
	private JTextArea list=new JTextArea(1,2);
	private String listnum;//스톱워치를 문자열로 표시할 수 있게 해준다.
	private JScrollPane scroll=new JScrollPane(list);//////////////////////////////////////////
	//타이머
	int k=0;//k가 1일때 타이머 동작
	int tmsec;//타이머에서 사용할 msec변수
	int tsec;//타이머에서 사용할 sec변수
	int tmin;//타이머에서 사용할 min변수
	int thour;//타이머에서 사용할 hour변수
	private JButton timer = new JButton("Timer");//스톱워치 메뉴시작버튼
	private JButton setTimer = new JButton("Set");//스톱워치 시작버튼
	private JButton Timerreset = new JButton("Reset");//스톱워치 시작버튼
	private JTextField timerh= new JTextField("시간을 입력하시오");//타이머에 필요한 정보를 입력받아 다른 변수에 전달
	private JTextField timerm = new JTextField("분을 입력하시오");//타이머에 필요한 정보를 입력받아 다른 변수에 전달
	private JTextField timers = new JTextField("초를 입력하시오");//타이머에 필요한 정보를 입력받아 다른 변수에 전달
	private JLabel dTimer = new JLabel(); //년,월,일을 표시해준다.
	//그 외
	private JButton homebutton = new JButton();//처음 화면으로 돌아간다.(처음보이던것만 보이게)
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menu.png")));//상단메뉴
	private ImageIcon exitprImage = new ImageIcon(Main.class.getResource("../images/exitButtonP.png"));//나가기버튼 눌렀을 때 이미지
	private ImageIcon exitunImage = new ImageIcon(Main.class.getResource("../images/exitButtonU.png"));//나가기버튼 누르지 않았을때 이미지
	
	private Image screenImage; // 
	private Graphics screenGraphic;//
	private Image background = new ImageIcon(Main.class.getResource("../images/background.png")).getImage();//배경이미지

	public SClock() //생성자
{
		// TODO 자동 생성된 생성자 스텁
		set(); //JFrame을 사용하기 위해 사용된 코드들이 들어있음
		
        setTextfield(); //텍스트필드로 구성된 대부분의 코드들이 들어있음 생성자가 실행시킴
      
		action(); //마우스이벤트의 코드들이 들어있으며 생성자에의해 실행됨
		buttonset();//버튼의 설정의 대부분이 들어있음 생성자가 실행시킴
		menu();
		//stopwatch();
		setlabel();//라벨관련 코드들이 들어있으며 생성자가 실행시킴
		//new Clock().start();	 
		gettime();//시간을 얻으며 알람이나 스톱워치등의 기능을 하는 코드들이 들어있고 생성자가 실행함
		
}
	public void setTextfield() {//텍스트필드의 설정
		Font nfont = new Font("나눔고딕", Font.BOLD, 15);//font설정3(적용이 필요함)
		list.setFont(nfont);//폰트설정
		list.setEditable(false);// textArea에 직접입력 불가
		a1.setFont(nfont);//폰트적용
		a2.setFont(nfont);//폰트적용
		a3.setFont(nfont);//폰트적용
		timerh.setFont(nfont);//폰트적용
		timerm.setFont(nfont);//폰트적용
		timers.setFont(nfont);//폰트적용
		shutdowntime.setFont(nfont);//폰트적용
		shutdownstring.setFont(nfont);//폰트적용
		
		timerh.setBounds(70,130,250,30);//timerh라는 텍스트 필드의 위치와 크기지정
		timerm.setBounds(70,160,250,30);//timerm이라는 텍스트필드의 위치와 크기지정
		timers.setBounds(70,190,250,30);//timers라는 텍스트필드의 위치와 크기지정
		
		list.setBounds(40,120,300,80);//위치와 크기지정
		
        a1.setBounds(70, 110, 250, 30);//a1이라는 텍스트필드의 위치와 크기지정
        a2.setBounds(70, 145, 250, 30);//a2이라는 텍스트필드의 위치와 크기지정
        a3.setBounds(70, 180, 250, 30);//a3이라는 텍스트필드의 위치와 크기지정
        shutdowntime.setBounds(80, 100, 250, 30);//shutdowntime이라는 텍스트필드의 위치와 크기지정
        shutdownstring.setBounds(80, 130, 250, 30);//shutdownstring이라는 텍스트필드의 위치와 크기지정
        shutdowntime.setVisible(false);//shutdowntime이 보이지 않게 설정해놓음
        shutdownstring.setVisible(false);//shutdownstring이 보이지 않게 설정해놓음
        a1.setVisible(false);//a1이 보이지 않게 설정
        a2.setVisible(false);//a2가 보이지 않게 설정
        a3.setVisible(false);//a3가 보이지 않게 설정
        list.setVisible(false);//list가 보이지 않게 설정
        timerh.setVisible(false);//timerh 가 보이지 않게 설정
        timerm.setVisible(false);//timerm 가 보이지 않게 설정
        timers.setVisible(false);//timers 가 보이지 않게 설정
        
        
        add(scroll);//프레임에 스크롤 추가
        add(list);//프레임에 리스트 추가
        add(timerh);//프레임에 timerh추가
        add(timerm);//프레임에 timerm추가
        add(timers);//프레임에 timers추가
        add(a1);//프레임에 a1추가
        add(a2);//프레임에 a2추가
        add(a3);//프레임에 a3추가
        add(shutdowntime);//프레임에 shutdowntime추가
        add(shutdownstring);//프레임에shutdownstring추가
      }//setTextfield()의 끝
	
	public void gettime() {//시간관련
		String Timerr;
		String stopwatch;//스톱워치에 사용할 문자열 변수
		String sday=null;//정수형으로 받은 요일을 문자로 변경
		Lstopwatch.setVisible(false);//Lstopwatch가 보이지 않게 한다.
		dTimer.setVisible(false);//버튼이 보이지 않게 해줌
		while(true) {//무조건 실행
			
			Calendar t=Calendar.getInstance();
			int year = t.get(Calendar.YEAR); //연도를 리턴
			int month = t.get(Calendar.MONTH);//월을 리턴
			int date = t.get(Calendar.DATE);//일을 리턴
			int amPm = t.get(Calendar.AM_PM);//오전/오후구분을 리턴
			int hour = t.get(Calendar.HOUR);//시를 리턴
			int min = t.get(Calendar.MINUTE);//분을 리턴
			int sec = t.get(Calendar.SECOND);//초를 리턴
			int msec = t.get(Calendar.MILLISECOND);//msec를 리턴 but.적용하지 않음.
			String ampm=amPm==Calendar.AM? "PM":"AM";//비교해서 pm이나 am을 ampm에 저장
			int day= t.get(Calendar.DAY_OF_WEEK);//요일을 정수형으로 리턴1=일~7은토
			switch(day) {//스위치문 정수형 day를 받는다.
			case 1: //1일때 
				sday="Sun";//sun으로 설정(일요일)
				break;//break;
			case 2://2일때
				sday="Mon"; //Mon으로 설정(월)
				break;//break;
			case 3://3일떄
				sday="Tus";//화요일
				break;//break;
			case 4://4일때
				sday="Wed";//수요일
				break;//break;
			case 5://5일때
				sday="Thu";//목요일
				break;//break;
			case 6://6일때
				sday="Fri";//금요일
				break;//break;
			case 7://7일때
				sday="Sat";//토요일
				break;//break;
			}
			one= (year+"."+month+"."+date+"."+sday+" day");//one이라는 문자열에 저장
			two=(ampm+" "+hour+":"+min+":"+sec+" sec");//two라는 문자열에 저장
			//System.out.println(year+"년 "+month+"월 "+date+"일 "+ampm+hour+"시 "+min+"분 "+sec+"."+msec+"초");//나오는지 확인차 사용
			first.setText(one);//first의 내용을 one(string)으로 설정한다
			second.setText(two);//second의 내용을 two(string)으로 설정한다.
			if(amPm==Alarm1&&hour==Alarm2&&min==Alarm3) {//알람기능 텍스트필드로 입력받은 데이터를 현재시간과 비교한다.
				if(count!=0) {//무한루프이기에 0.1초에 한번씩 노래가 실행되므로 카운트라는 변수를 이용해 1번만 실행가능하게 했다.
					Music Alarmsong = new Music("introMusic.mp3");//Music.class사용
					Alarmsong.start();//실행
					count--;		//실행하면 카운트를 내려서 0으로 만든다.
			}}
			if(i>=1) {//사용하지 않을때도 스톱워치가 작동하지 않게 하기 위해서 i를 선언했다.
				stop++;//msec를 계속 더한다.(sleep가 100이기때문에 10까지만 카운트한다.)
				if(stop>=10) { //10이상이면 실행 (10까지만 카운트)
					stop=stop-10; //10이 넘어가면 다시 0으로 만든다.
					ssec++;} //초를 하나 올린다.	
				if(ssec>=60) {//60이상이면 실행(60까지만 카운트)
					ssec=ssec-60;//60이상이면 60을 빼서 다시 0으로 만들어준다.
					smin++;}//분을 올린다.
				if(smin>=60) {//60이상이면 실행(60까지만 카운트)
					smin=smin-60;//60이상이면 60을 빼서 다시 0으로 만들어준다.
					shour++;}//시간을 올린다. 사실상 몇시간이상 사용할일이 없다고 판단해서 시간의 제한은 넣지않았다.
			}
			stopwatch=(shour+":"+smin+":"+ssec+"."+stop+" Sec");//스톱워치의 데이트를 문자열로 만든다
			Lstopwatch.setText(stopwatch);//Lstopwatch를 stopwatch(string)으로 설정해준다.
			if(k>=1) {//k가 1보다 크면 실행
				--tmsec;//while문에서 0.1초마다 실행하므로 msec
				if(tmsec<=0) {//msec가 0이되면 

						if(tsec>0||tmin>0||thour>0) {	//sec,min,hour중 하나가 0이 아니면	
							tmsec=tmsec+10;//msec를 10으로 설정해줌
							tsec--;//sec--해줌
							}
						if(tmsec<=0&&tsec<=0 &&tmin<=0 &&thour<=0) {//다 0이면
							tmsec=0;//0으로 설정
							tsec=0;//0으로 설정
							tmin=0;//0으로 설정
							thour=0;//0으로 설정
							Music buttonExit = new Music("introMusic.mp3");//알람실행
							buttonExit.start();//알람실행
							k--;}//k를 1빼주면서 실행중지
						
				}
				if(tsec<=0) {//sec가 0이면 
					if(tmin>0||thour>0) {//min,hour가 0보다 크면
					tsec=tsec+60;//초가 60올라감
					tmin--;}//분에서 1뺌
					else
						tsec=0;//아니면 0으로 설정
				}
				if(tmin<=0) {//분이 0이면
					if(thour>0) {//시간이 0이상일때
						tmin=tmin+60;//분이60분이 되고
						thour--;}//시간하나 마이너스
					else
						tmin=0;//아니면 분은 0
					
				}
				if(thour<=0) {//시간이 0이면
					thour=0;//그대로 0
				}
				}
			
			Timerr=(thour+":"+tmin+":"+tsec+"."+tmsec+" Sec");//시 분 초 msec
			dTimer.setText(Timerr);//timerr로 내용을 넣어준다.
			add(dTimer);//프레임에 추가
			add(Lstopwatch);//Lstopwatch를 프레임에 추가한다.
			add(first);//first를 프레임에 추가한다.
			add(second);//second를 프레임에 추가한다.
			try { //트라이 
				Thread.sleep(100);//0.1초
		    } catch(Exception e) {} //예외처리
		}//while문 끝
	}//gettime()끝
	public void setlabel() {//시간라벨 설정,알람 설명 라벨
		
		Font font = new Font("digital-7", Font.BOLD, 40);//font설정1(적용이 필요함)
		Font mfont = new Font("Dot Matrix", Font.BOLD, 20);//font설정2(적용이 필요함)
		
		homebutton.setText("Home");//버튼에 Home이라는 문자를 설정한다.
		homebutton.setFont(mfont);//mfont를 적용한다.
		homebutton.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		homebutton.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		shutdown.setText("OFF");//버튼에 off라는 문자를 설정한다.
		shutdown.setFont(mfont);//mfont를 적용한다.
		shutdown.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		shutdown.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		Alarm.setText("Alarm");//버튼에 Alarm이라는 문자를 설정한다.
		Alarm.setFont(mfont);//mfont를 적용한다.
		Alarm.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		Alarm.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		swstop.setText("stop");//버튼에 stop이라는 문자를 설정한다.
		swstop.setFont(mfont);//mfont를 적용한다.
		swstop.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		swstop.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		stopwatch.setText("Stopwatch");//버튼에 stopwatch라는 문자를 설정한다.
		stopwatch.setFont(mfont);//mfont를 설정한다.
		stopwatch.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		stopwatch.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		stopreset.setFont(mfont);//mfont를 설정한다.
		stopreset.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		stopreset.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		Timerreset.setFont(mfont);//mfont를 설정한다.
		Timerreset.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		Timerreset.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		shutdownunSet.setFont(mfont);//mfont를 설정한다.
		shutdownunSet.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		shutdownunSet.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		setTimer.setFont(mfont);//mfont를 설정한다.
		setTimer.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		setTimer.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		timer.setFont(mfont);//mfont를 설정한다.
		timer.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		timer.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		Set.setFont(mfont);//mfont를 설정한다.
		Set.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		Set.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		shutdownSet.setFont(mfont);//mfont를 설정한다.
		shutdownSet.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		shutdownSet.setForeground(Color.white);//문자를 하얀색으로 설정한다.
		
		stopstart.setText("start");//버튼에 start라는 문자를 설정한다.
		stopstart.setFont(mfont);//mfont를 적용한다.
		stopstart.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		stopstart.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		second.setOpaque(true);//라벨을 불투명하게 설정
		second.setFont(font);//font를 적용한다.
		second.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		second.setBounds(85,170,300,50);//위치와 크기를 설정한다.
		second.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		Lstopwatch.setOpaque(true);//라벨을 불투명하게 설정
		Lstopwatch.setFont(font);//font를 적용한다.
		Lstopwatch.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		Lstopwatch.setBounds(80,70,200,50);//위치와 크기를 설정한다.
		Lstopwatch.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		dTimer.setOpaque(true);//라벨을 불투명하게 설정
		dTimer.setFont(font);//font를 적용한다.
		dTimer.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		dTimer.setBounds(80,60,320,50);//위치와 크기를 설정한다.
		dTimer.setForeground(Color.white);//글자색을 하얀색으로 설정한다.
		
		first.setOpaque(true);//라벨을 불투명하게 설정
		first.setFont(font);//font를 적용한다.
		first.setBackground(new Color(0,0,0,0));//백그라운드를 0,0,0,0으로 설정한다.
		first.setBounds(60,120,320,50);//위치와 크기를 설정한다.
		first.setForeground(Color.white);//글자색을 하얀색으로 설정한다.

	}
	public void buttonset() {//여기부터 버튼설정
		Alarm.setBorderPainted(false);//버튼 테두리 설정 해제
		Alarm.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		Alarm.setFocusPainted(false);//선택했던 버튼표시 제거
		
		Timerreset.setBorderPainted(false);//버튼 테두리 설정 해제
		Timerreset.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		Timerreset.setFocusPainted(false);//선택했던 버튼표시 제거
		
		timer.setBorderPainted(false);//버튼 테두리 설정 해제
		timer.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		timer.setFocusPainted(false);//선택했던 버튼표시 제거
		
		setTimer.setBorderPainted(false);//버튼 테두리 설정 해제
		setTimer.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		setTimer.setFocusPainted(false);//선택했던 버튼표시 제거
		
		stopstart.setBorderPainted(false);//버튼 테두리 설정 해제
		stopstart.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		stopstart.setFocusPainted(false);//선택했던 버튼표시 제거
		
		stopreset.setBorderPainted(false);//버튼 테두리 설정 해제
		stopreset.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		stopreset.setFocusPainted(false);//선택했던 버튼표시 제거
		
		stopwatch.setBorderPainted(false);//버튼 테두리 설정 해제
		stopwatch.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		stopwatch.setFocusPainted(false);//선택했던 버튼표시 제거
		
		Set.setBorderPainted(false);//버튼 테두리 설정 해제
		Set.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		Set.setFocusPainted(false);//선택했던 버튼표시 제거
		
		shutdownunSet.setBorderPainted(false);//버튼 테두리 설정 해제
		shutdownunSet.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		shutdownunSet.setFocusPainted(false);//선택했던 버튼표시 제거
		
		homebutton.setBorderPainted(false);//버튼 테두리 설정 해제
		homebutton.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		homebutton.setFocusPainted(false);//선택했던 버튼표시 제거
		
		
		shutdownSet.setBorderPainted(false);//버튼 테두리 설정 해제
		shutdownSet.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		shutdownSet.setFocusPainted(false);//선택했던 버튼표시 제거
		
		shutdown.setBorderPainted(false);//버튼 테두리 설정 해제
		shutdown.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		shutdown.setFocusPainted(false);//선택했던 버튼표시 제거
		
		swstop.setBorderPainted(false);//버튼 테두리 설정 해제
		swstop.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		swstop.setFocusPainted(false);//선택했던 버튼표시 제거
		
		exitbutton.setBorderPainted(false);//버튼 테두리 설정 해제
		exitbutton.setContentAreaFilled(false);//이미지 테두리 효과를 없앰
		exitbutton.setFocusPainted(false);//선택했던 버튼표시 제거
		
		//버튼위치 지정
		Timerreset.setBounds(100,230,100,50);//버튼크기,위치설정
		setTimer.setBounds(200,230,100,50);//버튼크기,위치설정
		timer.setBounds(320,20,100,40);//버튼크기,위치설정
		swstop.setBounds(50,200,100,50);//버튼크기,위치설정
		stopstart.setBounds(220,200,130,50);//버튼크기,위치설정
		Alarm.setBounds(60,20,100,40);//버튼크기,위치설정
		Set.setBounds(150,220,80,50);//버튼크기,위치설정
		shutdownSet.setBounds(100,200,80,50);//버튼크기,위치설정
		shutdownunSet.setBounds(200,200,110,50);//버튼크기,위치설정
		shutdown.setBounds(140,20,70,40);//버튼크기,위치설정
		exitbutton.setBounds(380, 0, 20, 20);//버튼크기,위치설정
		homebutton.setBounds(-10, 20, 90,40);//버튼크기,위치설정
		stopwatch.setBounds(190,20,150,40);//버튼크기,위치설정
		stopreset.setBounds(120,200,130,50);//버튼크기,위치설정
		//설정(확인버튼)
		Timerreset.setVisible(false);//보이지 않게하기
		shutdownSet.setVisible(false);//보이지 않게하기
		swstop.setVisible(false);//보이지 않게하기
		stopstart.setVisible(false);//보이지 않게하기
		Set.setVisible(false);//보이지 않게하기
		shutdownunSet.setVisible(false);//보이지 않게하기
		stopreset.setVisible(false);//보이지 않게하기
		setTimer.setVisible(false);//보이지 않게하기
		//버튼추가
		add(Timerreset);//JFrame에 추가
		add(setTimer);//JFrame에 추가
		add(timer);//JFrame에 추가
		add(stopreset);//JFrame에 추가
		add(shutdownunSet);//JFrame에 추가
		add(swstop);//JFrame에 추가
		add(Alarm);//JFrame에 추가
		add(stopstart);//JFrame에 추가
		add(Set);//JFrame에 추가
		add(stopwatch);//JFrame에 추가
		add(shutdown);//JFrame에 추가
		add(shutdownSet);//JFrame에 추가
		add(exitbutton);//JFrame에 추가
		add(homebutton);//JFrame에 추가
	}
	public void action() {		//마우스 눌렀을때 올렸을때 내렸을때
		exitbutton.addMouseListener(new MouseAdapter() { //나가기
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 들어갔을때
				exitbutton.setIcon(exitprImage); //이미지 설정
				exitbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//노래 
				buttonEnter.start();}//노래 시작
			@Override
			public void mouseExited(MouseEvent e) { //마우스 나왔을때 
				exitbutton.setIcon(exitunImage);//마우스 나왔을때 이미지 설정
				exitbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//마우스 눌렀을때 소리
				buttonExit.start();//소리 시작
				try {//예외처리문
					Thread.sleep(1000);//1초
				} catch (InterruptedException ex) {//예외처리
					ex.printStackTrace();}//예외처리
				System.exit(0);}});//프로그램 종료
		//
		homebutton.addMouseListener(new MouseAdapter() { //셧다운
			@Override
			public void mouseEntered(MouseEvent e) { //마우스 들어갔을때 		
				homebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//마우스 들어갔을때 소리
				buttonEnter.start();}//소리 시작
			@Override
			public void mouseExited(MouseEvent e) {//마우스 나왔을때 		
				homebutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서 설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//노래선택
				buttonExit.start();//음악시작
				first.setVisible(true);//버튼이 보이게
				second.setVisible(true);//버튼이 보이게
				shutdowntime.setVisible(false);//버튼이 보이지 않게 해준다.
		        shutdownstring.setVisible(false);//버튼이 보이지 않게 해준다.
		        shutdownSet.setVisible(false);//버튼이 보이지 않게 해준다.
		        a1.setVisible(false);//버튼이 보이지 않게 해준다.
		        a2.setVisible(false);//버튼이 보이지 않게 해준다.
		        a3.setVisible(false);//버튼이 보이지 않게 해준다.
		        Set.setVisible(false);//버튼이 보이지 않게 해준다.
		        swstop.setVisible(false);//버튼이 보이지 않게 해준다.
		        stopstart.setVisible(false);//버튼이 보이지 않게 해준다.
		        shutdownunSet.setVisible(false);//버튼이 보이지 않게 해준다.
		        stopreset.setVisible(false);//버튼이 보이지 않게 해준다.
		        setTimer.setVisible(false);//버튼이 보이지 않게 해준다.
		        Timerreset.setVisible(false);//버튼이 보이지 않게 해준다.
		        timerh.setVisible(false);//timerh 가 보이지 않게 설정
		        timerm.setVisible(false);//timerm 가 보이지 않게 설정
		        timers.setVisible(false);//timers 가 보이지 않게 설정
		        dTimer.setVisible(false);//버튼이 보이지 않게 해준다.
		        list.setVisible(false);//버튼이 보이지 않게 해준다.
			}});
		stopwatch.addMouseListener(new MouseAdapter() { //셧다운
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 들어왔을때
				stopwatch.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//음악 설정
				buttonEnter.start();}//음악 시작
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을때
				stopwatch.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스이벤트
				Music buttonExit = new Music("open.mp3");//알림음악설정
				buttonExit.start();//음악시작
				first.setVisible(false);//버튼이 보이지 않게 해준다.
				second.setVisible(false);//버튼이 보이지 않게 해준다.
				shutdowntime.setVisible(false);//버튼이 보이지 않게 해준다.
		        shutdownstring.setVisible(false);//버튼이 보이지 않게 해준다.
		        shutdownSet.setVisible(false);//버튼이 보이지 않게 해준다.
		        a1.setVisible(false);//버튼이 보이지 않게 해준다.
		        a2.setVisible(false);//버튼이 보이지 않게 해준다.
		        a3.setVisible(false);//버튼이 보이지 않게 해준다.
		        Set.setVisible(false);//버튼이 보이지 않게 해준다.
		        Lstopwatch.setVisible(true);//버튼이 보이게 해준다.
		        stopstart.setVisible(true);//버튼이 보이게 해준다.
		        swstop.setVisible(true);//버튼이 보이게 해준다.
		        shutdownunSet.setVisible(false);//버튼이 보이지 않게 해준다.
		        stopreset.setVisible(true);//버튼이 보이지 않게 해준다.
		        setTimer.setVisible(false);//버튼이 보이지 않게 해준다.
		        Timerreset.setVisible(false);//버튼이 보이지 않게 해준다.
		        timerh.setVisible(false);//timerh 가 보이지 않게 설정
		        timerm.setVisible(false);//timerm 가 보이지 않게 설정
		        timers.setVisible(false);//timers 가 보이지 않게 설정
		        dTimer.setVisible(false);//버튼이 보이지 않게 해준다.
		        list.setVisible(true);//버튼이 보이게 해준다.
			}});
		//////
		stopstart.addMouseListener(new MouseAdapter() { //셧다운
			@Override
			public void mouseEntered(MouseEvent e) { // 	마우스 들어갔을때			
				stopstart.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//음악 설정
				buttonEnter.start();}//음악 시작
			@Override
			public void mouseExited(MouseEvent e) {//마우스 나왔을때 				
				stopstart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//음악 선택
				buttonExit.start();//음악 시작
				i=1;}});//i를 1로 설정
		//////
		shutdown.addMouseListener(new MouseAdapter() { //셧다운 마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 		마우스 들어갔을때	
				shutdown.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3"); //음악 설정
				buttonEnter.start();}//음악 시작
			@Override
			public void mouseExited(MouseEvent e) {//마우스 나왔을때 				
				shutdown.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//마우스 눌렀을때 소리 설정
				buttonExit.start();//소리 시작
				first.setVisible(false);//버튼이 보이지 않게 해준다.
				second.setVisible(false);//버튼이 보이지 않게 해준다.
				shutdowntime.setVisible(true);//버튼이 보이게 한다.
		        shutdownstring.setVisible(true);//버튼이 보이게 한다.
		        shutdownSet.setVisible(true);//버튼이 보이게 한다.
		        a1.setVisible(false);//버튼이 보이지 않게 해준다.
		        a2.setVisible(false);//버튼이 보이지 않게 해준다.
		        a3.setVisible(false);//버튼이 보이지 않게 해준다.
		        Set.setVisible(false);//버튼이 보이지 않게 해준다.
		        Lstopwatch.setVisible(false);//버튼이 보이지 않게 해준다.
		        swstop.setVisible(false);//버튼이 보이지 않게 해준다.
		        stopstart.setVisible(false);//버튼이 보이지 않게 해준다.
		        shutdownunSet.setVisible(true);//버튼이 보이게 한다.
		        stopreset.setVisible(false);//버튼이 보이지 않게 해준다.
		        setTimer.setVisible(false);//버튼이 보이지 않게 해준다.
		        Timerreset.setVisible(false);//버튼이 보이지 않게 해준다.
		        timerh.setVisible(false);//timerh 가 보이지 않게 설정
		        timerm.setVisible(false);//timerm 가 보이지 않게 설정
		        timers.setVisible(false);//timers 가 보이지 않게 설정
		        dTimer.setVisible(false);//버튼이 보이지 않게 해준다.
		        list.setVisible(false);//버튼이 보이지 않게 해준다.
			}});
		/////////
		Alarm.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 	마우스가 들어갔을때			
				Alarm.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//음악설정
				buttonEnter.start();}//설정된 음악 실행
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나왔을때
				Alarm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을때
				Music buttonExit = new Music("open.mp3");//음악설정
				buttonExit.start();//설정된 음악 시작
				Alarmlabel.setVisible(true);//버튼이 보이게 한다.
				first.setVisible(false);//버튼이 보이지 않게 한다.
				second.setVisible(false);//버튼이 보이지 않게 한다.
			    a1.setVisible(true);//버튼이 보이게 한다.
		        a2.setVisible(true);//버튼이 보이게 한다.
		        a3.setVisible(true);//버튼이 보이게 한다.
				Set.setVisible(true);//버튼이 보이게 한다.
				shutdowntime.setVisible(false);//버튼이 보이게 한다.
		        shutdownstring.setVisible(false);//버튼이 보이게 한다.
		        shutdownSet.setVisible(false);//버튼이 보이게 한다.
		        Lstopwatch.setVisible(false);//버튼이 보이게 한다.
		        swstop.setVisible(false);//버튼이 보이게 한다.
		        stopstart.setVisible(false);//버튼이 보이게 한다.
		        shutdownunSet.setVisible(false);//버튼이 보이게 한다.
		        stopreset.setVisible(false);//버튼이 보이게 한다.
		        setTimer.setVisible(false);//버튼이 보이게 한다.
		        Timerreset.setVisible(false);//버튼이 보이게 한다.
		        timerh.setVisible(false);//timerh 가 보이지 않게 설정
		        timerm.setVisible(false);//timerm 가 보이지 않게 설정
		        timers.setVisible(false);//timers 가 보이지 않게 설정
		        dTimer.setVisible(false);//버튼이 보이게 한다.
		        list.setVisible(false);//버튼이 보이게 한다.
			}});
		///
		timer.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 들어갔을때				
				timer.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//음악 설정
				buttonEnter.start();}//음악을 실행한다.
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을때
				timer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을때
				Music buttonExit = new Music("open.mp3");//음악 설정
				buttonExit.start();//음악 시작
				Alarmlabel.setVisible(false);//보이지 않게 하기
				first.setVisible(false);//보이지 않게 하기
				second.setVisible(false);//보이지 않게 하기
			    a1.setVisible(false);//보이지 않게 하기
		        a2.setVisible(false);//보이지 않게 하기
		        a3.setVisible(false);//보이지 않게 하기
				Set.setVisible(false);//보이지 않게 하기
				shutdowntime.setVisible(false);//보이지 않게 하기
		        shutdownstring.setVisible(false);//보이지 않게 하기
		        shutdownSet.setVisible(false);//보이지 않게 하기
		        Lstopwatch.setVisible(false);//보이지 않게 하기
		        swstop.setVisible(false);//보이지 않게 하기
		        stopstart.setVisible(false);//보이지 않게 하기
		        shutdownunSet.setVisible(false);//보이지 않게 하기
		        stopreset.setVisible(false);//보이지 않게 하기
		        setTimer.setVisible(true);//버튼이 보이게 한다.
		        timerh.setVisible(true);//timerh 가 보이지 않게 설정
		        timerm.setVisible(true);//timerm 가 보이지 않게 설정
		        timers.setVisible(true);//timers 가 보이지 않게 설정
		        dTimer.setVisible(true);//버튼이 보이게 한다.
		        Timerreset.setVisible(true);//버튼이 보이게 한다.
		        list.setVisible(false);//보이지 않게 하기
			}});
		//
		swstop.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 들어갔을때			
				swstop.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 커서 변경
				Music buttonEnter = new Music("올렸을때.mp3");//버튼음 설정
				buttonEnter.start();}//버튼음 실행
			@Override
			public void mouseExited(MouseEvent e) { 				//마우스 나갔을때
				swstop.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//마우스 커서 변경
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//버튼음 설정
				buttonExit.start(); //버튼음 시작
				if(i>=1)//i가 1보다 클때
					i--;//무한대로 실행되지 않게 해줌
				
				listnum=(shour+":"+smin+":"+ssec+"."+stop+"\n");//listnum으로 모아서 스트링으로 바꿈
				list.append(listnum);//list에 listnum의 내용을 추가
				}});
		//
		stopreset.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 	마우스 들어왔을때			
				stopreset.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 커서
				Music buttonEnter = new Music("올렸을때.mp3");//음악 선택
				buttonEnter.start();}//음악 시작
			@Override
			public void mouseExited(MouseEvent e) { 				//마우스 나왔을때
				stopreset.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서선택
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//버튼음악설정
				buttonExit.start();//음악 시작
				if(i>=1)//i가 1보다 크면 실행함
					i--;//0으로 만들어서 무한대로 돌아가지 않게 해줌
				stop=0; //스톱워치에 사용할 변수  msec(1자리)
				ssec=0;//스톱워치에 사용할 변수  초
				smin=0;//스톱워치에 사용할 변수  분
				shour=0;//스톱워치에 사용할 변수 시간
				list.setText("");//list의 내용을 빈칸으로 설정해준다.
				}});
		Timerreset.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 	마우스 들어갔을때			
				Timerreset.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//버튼 들어갔을때
				buttonEnter.start();}//버튼 들어갔을때
			@Override
			public void mouseExited(MouseEvent e) { 				//마우스 나왔을때
				Timerreset.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//음악 설정
				buttonExit.start();//음악 실행
				if(k>=1)//케이가 1보다 크면 실행
					k--;//k--를 해서 스톱워치가 무한정실행되지 않게 해줌
				tmsec=0; //스톱워치에 사용할 변수  msec(1자리)
				tsec=0;//스톱워치에 사용할 변수  초
				tmin=0;//스톱워치에 사용할 변수  분
				thour=0;//스톱워치에 사용할 변수 시간
				}});
		//
		setTimer.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 				마우스 들어왔을때
				setTimer.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//음악설정
				buttonEnter.start();}//음악시작
			@Override
			public void mouseExited(MouseEvent e) { 	//마우스 나갔을때			
				setTimer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//버튼음 설정
				buttonExit.start();//버튼음 재생
				k++;//k++해서 스톱워치가 사용하능하게 해줌
				tsec=Integer.parseInt(timers.getText());//스톱워치에 사용할 변수  초
				tmin=Integer.parseInt(timerm.getText());//스톱워치에 사용할 변수  분
				thour=Integer.parseInt(timerh.getText());//스톱워치에 사용할 변수 시간
				
				}});
		//
		Set.addMouseListener(new MouseAdapter() { //마우스 이벤트 추가
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 들어왔을때
				
				Set.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//버튼음 설정
				buttonEnter.start();}//버튼음 시작
			@Override
			public void mouseExited(MouseEvent e) { 			//마우스가 버튼에서 나갔을때	
				Set.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을때
				Music buttonExit = new Music("open.mp3");//반복하지 않고 버튼음을 재생한다.
				buttonExit.start();//버튼음 시작
				first.setVisible(false);//보이지 않게 해준다.
				second.setVisible(false);//보이지 않게 해준다.
				count++; //카운트를 올려서 알람을 설정할 수 있게 해준다.
				Alarm1=Integer.parseInt(a1.getText());//인테저형으로 받아서 저장
				Alarm2=Integer.parseInt(a2.getText());//인테저형으로 받아서 저장
				Alarm3=Integer.parseInt(a3.getText());//인테저형으로 받아서 저장
				}});
		//
		shutdownSet.addMouseListener(new MouseAdapter() { //마우스 이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 버튼에 들어왔을때				
				shutdownSet.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//버튼음 설정
				buttonEnter.start();}//버튼음 시작
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 버튼에서 나갔을때 				
				shutdownSet.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				Music buttonExit = new Music("open.mp3");//버튼음 설정
				buttonExit.start();//버튼음 시작
				first.setVisible(false);//안보이게
				second.setVisible(false);		//안보이게
				time=Integer.parseInt(shutdowntime.getText());//텍스트필드에 있는 내용을 인테저로 바꿔서 time에 저장
				word=shutdownstring.getText(); //문자열을받아서 word에 저장
				computeroff();}});//컴퓨터를 종료하는 메소드를 불러온다.
		//
		shutdownunSet.addMouseListener(new MouseAdapter() { //마우스이벤트
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 들어왔을때			
				shutdownunSet.setCursor(new Cursor(Cursor.HAND_CURSOR));//커서설정
				Music buttonEnter = new Music("올렸을때.mp3");//한번만 실행되는 버튼음
				buttonEnter.start();}//버튼음 시작
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 해당 버튼에서 나갔을때	
				shutdownunSet.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}//커서설정
			@Override
			public void mousePressed(MouseEvent e) {//마우스로 눌렀을때
				Music buttonExit = new Music("open.mp3");//한번만 반복한다.
				buttonExit.start(); //노래를 실행한다.
				first.setVisible(false);//보이지 않게 한다.
				second.setVisible(false);		//보이지 않게 한다.
				time=Integer.parseInt(shutdowntime.getText()); //인테저형으로 변환해서 타임에 넣는다.
				word=shutdownstring.getText(); //shutdownstring에서 word에 받은 문자열을 넣는다.
				computeron();}}); //셧다운 해제를 위한 메소드를 호출
		//
		a1.addMouseListener(new MouseAdapter() { //	마우스이벤트			
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				a1.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		a2.addMouseListener(new MouseAdapter() { //		마우스이벤트		
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				a2.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		a3.addMouseListener(new MouseAdapter() { //	마우스이벤트	
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				a3.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		timerm.addMouseListener(new MouseAdapter() { //	마우스이벤트	
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				timerm.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		timers.addMouseListener(new MouseAdapter() { //	마우스이벤트	
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				timers.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		timerh.addMouseListener(new MouseAdapter() { //	마우스이벤트	
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				timerh.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		shutdowntime.addMouseListener(new MouseAdapter() { //마우스이벤트		
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				shutdowntime.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		shutdownstring.addMouseListener(new MouseAdapter() { //마우스이벤트	
			public void mousePressed(MouseEvent e) {//마우스가 눌렀을때
				shutdownstring.setText("");	//마우스로 누르면 빈칸으로 변함
			}});
		}
	public void set() {
		setUndecorated(true);//프레임 안보이게 해줌
		setTitle("Y_Clock"); // 타이틀
		setSize(Main.SCREENW, Main.SCREENH); // 메인에서 가로 세로 크기 가져옴
		setLocationRelativeTo(null);// 화면 가운데서 창이 나옴
		setResizable(false);//정해진사이즈에서 변경불가
		setLayout(null);//레이아웃 설정가능
		setBackground(new Color(0, 0, 0, 0));//배경설정
		setVisible(true); // 보이게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 안정적으로 jframe이 종료되게 해줌
		
	}
	public void computeroff() {//컴퓨터 종료를 위해 구현
		try {//예외처리구문
			Runtime.getRuntime().exec("shutdown -s -t "+time+" -c \""+word+"\"");//shutdowns명령어를 실행
        } catch (Exception e) {}}
	public void computeron() {//컴퓨터 종료를 해제하기 위해 구현
		try {//예외처리
			Runtime.getRuntime().exec("shutdown -a");//shutdowns명령어를 취소
        } catch (Exception e) {}}
	public void menu() { // 상단메뉴
		menuBar.setBounds(0, 0, 400, 20);//메뉴 위치,크기지정
		menuBar.addMouseListener(new MouseAdapter() {//마우스 이벤트
			@Override
			public void mousePressed(MouseEvent e) {//마우스 눌렀을때
				mouseX = e.getX(); //x좌표를 받아 mousex에 넣는다.
				mouseY = e.getY();}});//y좌표를 받아 mousey에 넣는다.
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {//마우스 이벤트
			@Override
			public void mouseDragged(MouseEvent e) {//마우스로 드래그했을때
				int x = e.getXOnScreen();//스크린 x좌표
				int y = e.getYOnScreen();//스크린 y좌표
				setLocation(x - mouseX, y - mouseY);}}); //위치 계산
		add(menuBar);}//메뉴바를 프레임에 추가
	public void paint(Graphics g) {//그리는 함수(더블버퍼형식)
		screenImage=createImage(Main.SCREENW, Main.SCREENH);//400,300의 크기의 이미지만듬
		screenGraphic=screenImage.getGraphics();//screenGraphic에 넣어줌
		screenDraw(screenGraphic);//screenDraw부름
		g.drawImage(screenImage, 0, 0, null);}//screenimage를 그려줌
	public void screenDraw(Graphics g) {//screendraw함수
		g.drawImage(background, 0 ,0 ,null);//배경그려줌
		paintComponents(g);//add로 추가해준부분을 그려줌
		this.repaint();}}//다시그려줌
