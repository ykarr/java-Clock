package clock01;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Music extends Thread{//프로그램안에 있는 작은 프로그램
	private Player player;//자바줌에있던 라이브러리중하나
	
	private File file; //I/o
	private FileInputStream f;//파일스트림 준비
	private BufferedInputStream b;//버퍼 스트림준비
	
	public Music(String name) {//생성자
		try {//예외처리
			file=new File(Main.class.getResource("../music/"+name).toURI());//uri파일경로
			f=new FileInputStream(file); //FileInputStream f=new FileInputStream(file);
			b=new BufferedInputStream(f);//위에서 private로 정의함
			player=new Player(b);//해당파일을 받을 수 있게 해줌
		}catch(Exception e) {//try문에서 오류가 발생하면 catch로
			System.out.println(e.getMessage());//오류가발생했을때 출력
		}
	}
	public void close() {//음악이 언제 실행되던간에 항상 종료할수 있게 해줌
		player.close();	
		this.interrupt(); //곡을 실행하는 프로그램을 종료
	}
	@Override
	public void run() { //Thread라는 함수를 상속받으면 무조건 사용해야 한다고 한다.
		try {//예외처리
				player.play();//곡을 실행
				f=new FileInputStream(file);//f=파일경로를 입력받고 
				b=new BufferedInputStream(f);//출력할 파일 객체를 지정하고 버퍼 입력 객체 생성
				player=new Player(b);//player 실행 
		}catch(Exception e) {
			System.out.println(e.getMessage());//오류가 발생하면 실행
		}
	}
}
