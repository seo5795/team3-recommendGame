package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.GameDownVO;
import model.GameVO;
import model.UserVO;

public class GameView {

	Scanner sc=new Scanner(System.in);
	public int act; // 사용자가 원하는 행동을 수행해주도록 버튼(정수)으로 입력받을 변수 : act

	// ↓ 새로운 메소드 선언 , 다른패키지에서도 접근 할 수 있도록 접근제어자 public 붙여줌
	//                 (controller)
	
// 1. 메인화면 View--------------------------------------------
	
	// (1) NCGames실행시 처음 보여줄 화면
	public void startView() { 
			 System.out.println();
	         System.out.println("★★NCGames★★");
	         while(true) {
	         System.out.println("1. 로그인");
	         System.out.println("2. 회원가입");
	         System.out.println("3. 종료");
	         System.out.print(">>> ");
	         try {
	            act=sc.nextInt();
	            if(act<1 || act>3) {
	               throw new Exception("1~3 사이 정수를 입력하세요!");
	            }
	         }catch(InputMismatchException e) {
	            sc.nextLine();
	            System.out.println("예외발생: 타입확인!");
	            System.out.println();
	            continue;
	         }catch(Exception e) {
	            System.out.println("예외발생: "+e.getMessage());
	            System.out.println();
	            continue;
	         }
	         break;
	      }
	   }
	
	//UserVo의 객체 uvo를 사용하기 위함
	UserVO uvo = new UserVO();
	
//------------------------------------------------------------	
	// 현재 [Controller]에선 회원가입,로그인 입력 메서드가 id만 입력받게 되어있어
	// 테스트용 (★☆★★☆★추후 삭제★☆★★☆★)
	public UserVO insertUser() { 
		System.out.println();
		System.out.println("회원가입하실 아이디를 입력하세요.");
		System.out.print("아이디: ");
		String id = sc.next();
		//id를 uvo에 넣어야함
		uvo.setUid(id);
		return uvo;
	}
	public String loginUser() { 
		System.out.println();
		System.out.println("로그인하실 아이디를 입력하세요.");
		System.out.print("아이디: "); 
		String id = sc.next();
		return id;
	}
//---------------------------------------------------------	
	
	
	// (2)-1 회원가입:id입력 메서드 //set = 설정, get = 값을 가져오는 것
	public String insertUserId() { //■■■수정■■■
		System.out.println();
		System.out.println("회원가입하실 아이디를 입력하세요.");
		System.out.print("아이디: ");
		String id = sc.next();
		return id;
	}
	
	// (2)-2 회원가입:pw입력 메서드
	public String insertUserPw() { //■■■수정■■■
		System.out.println("회원가입하실 비밀번호를 입력하세요.");
		System.out.print("비밀번호: ");  
		String pw = sc.next();		
		return pw;
	}
	
	// (3)-1 로그인:id입력 메서드 //[day23]참고
	public String loginUserId() { //■■■수정■■■
		System.out.println();
		System.out.println("로그인하실 아이디를 입력하세요.");
		System.out.print("아이디: ");  //xxx수정xxx
		String id = sc.next();
		return id;
	}
	//메서드 타입을 String으로해서 id를 입력받아 return
	//[C]에서 String uid=view.loginUser();를 통해 입력받은 아이디를->uid에 저장
	
	// (3)-2 로그인:pw입력 메서드
	public String loginUserPw() { //■■■수정■■■
		System.out.println("로그인하실 비밀번호를 입력하세요.");
		System.out.print("비밀번호: ");  
		String pw = sc.next();
		return pw;
	}
	
	
	// 로그인 성공시 보여줄 메서드
	public void processSuccess() {
		System.out.println("수행 성공하였습니다.");
	}
	
	// 로그인 실패시 보여줄 메서드
	public void processFail(){
		System.out.println("수행 실패하였습니다.");
	}
	
	// 수행 종료시 보여줄 메서드(소비자)
	public void processCancel() {
		System.out.println();
		System.out.println("종료합니다.");
		for(int i=0;i<5;i++) {
			System.out.println(".");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("다음에 또 이용해주세요~!");
	}
	
	
// 2-1. 소비자 View-------------------------------------------
	
	// (1)소비자에게 보여줄 메서드(로그인 성공 후 진행)
	public void clientView() {
		System.out.println();
		while(true) {
			System.out.println("<카테고리에 따른 게임 추천>"); //단위 적자
			System.out.println("(단위 - 가격: 원 | 할인율: % | 할인가: 원 | 다운로드수,추천수: 개)");
			System.out.println("1. 장르");
			System.out.println("2. 추천순");
			System.out.println("3. 가격순");
			System.out.println("4. 할인율");
			System.out.println("5. 종료");
			System.out.print("카테고리 선택>>> ");
			try {
				act=sc.nextInt();
				if(act<1 || act>5) {
					throw new Exception("1~5사이 정수를 입력하세요!");
				}
			}catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("예외발생: 타입확인!");
				continue;
			}catch(Exception e) {
				System.out.println("예외발생: "+e.getMessage());
				continue;
			}
			break;
		}
	}
	

	
// 2-2
		
	// (1) 할인율로 게임 추천목록 보여주는 메서드
	public void listDiscount(ArrayList<GameVO> datas) {   // [예시-day21]
		System.out.println();
		System.out.println("===할인율===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// 장르에따른 게임추천 세분화 
	// (2) 장르에 따른 게임추천 (★★추가★★)
	public void listGenre() {
		while(true) {
		System.out.println();
		System.out.println("어떤 장르로 검색하시겠습니까?");
		System.out.println("1.액션");
		System.out.println("2.어드벤쳐");
		System.out.println("3.캐쥬얼");
		System.out.println("4.소설");
		System.out.println("5.레이싱");
		System.out.println("6.RPG");
		System.out.println("7.시뮬레이션");
		System.out.print("장르선택>>> ");
        try {
            act=sc.nextInt();
            if(act<1 || act>7) {
               throw new Exception("1~7사이 정수를 입력하세요!");
            }
         }catch(InputMismatchException e) {
            sc.nextLine();
            System.out.println("예외발생: 타입확인!");
            continue;
         }catch(Exception e) {
            System.out.println("예외발생: "+e.getMessage());
            continue;
         }
         break;
      }
   }
	
	// (2)-1 장르:액션 게임추천목록 (★★추가★★)
	public void action(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===액션===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (2)-2 장르:어드벤쳐 게임목록추천  (★★추가★★)
	public void adventure(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===어드벤쳐===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	  
	// (2)-3 장르:캐쥬얼 게임추천목록  (★★추가★★)
	public void casual(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===캐쥬얼===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (2)-4 장르:소설 게임추천목록  (★★추가★★)
	public void novel(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===소설===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (2)-5 장르:레이싱 게임추천목록  (★★추가★★)
	public void racing(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===레이싱===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (2)-6 장르:RPG 게임추천목록  (★★추가★★)
	public void rpg(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===RPG===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (2)-7 장르:시뮬레이션 게임추천목록  (★★추가★★)
	public void simulation(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===소설===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	

	// (3) 출시일에 따른 게임추천목록 ◆
	public void listDate(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===출시일===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (4) 추천 수에 따른 게임추천목록
	public void listRcnt(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===추천수===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}
	
	// (5) 가격에 따른 게임추천목록
	public void listPrice(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===가격순===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}

	// 2-3
	
	// (0) 현재 카테고리에서 조회 or 다른 카테고리에 따른 게임추천목록 보기 
	// "카테고리에 따른 리스트 출력메서드" 5가지 중 어느하나라도 실행시 같이 출력
	public void choose() {
		System.out.println("----------------------------------------------------");
		System.out.println("위의 목록에서 게임을 조회하시겠습니까? ");
		while(true) {
		System.out.println("[1.위 목록에서 조회 / 2.다른 카테고리를 통해 조회]");
		System.out.print("숫자로 입력>>> ");	
        try {
            act=sc.nextInt();
            if(act<1 || act>2) {
               throw new Exception("범위 외 입력!");
            }
         }catch(InputMismatchException e) {
            sc.nextLine();
            System.out.println("예외발생: 타입확인!");
            continue;
         }catch(Exception e) {
            System.out.println("예외발생: "+e.getMessage());
            continue;
         }
         break;
      }
   }
	// 2를 눌렀다면 clientView()로 돌아감
	
	// [위 목록에서 조회:1]을 눌렀다면 실행
	// (1)게임조회 메서드
	public int selectGame() {
		System.out.println();
		System.out.print("조회할 게임번호 입력>>> ");
		int num=sc.nextInt();
		return num; // num을 리턴받은 후-> [C]에서 gnum에 담고 gvo.setGnum(gnum)
	}
	
	// (2)★다운로드 여부 메서드
	public void download(GameVO gvo) {
		System.out.println();
		System.out.println("조회하신 게임의 정보는 다음과 같습니다.");
		System.out.println(gvo);//다운로드할 게임정보 출력
		while(true) {
		System.out.println();
		System.out.println("해당 게임을 다운로드 하시겠습니까? [1.예 / 2.아니요]");
		System.out.print("숫자로 입력>>> ");	
        try {
            act=sc.nextInt();
            if(act<1 || act>2) {
               throw new Exception("범위외입력!");
            }
         }catch(InputMismatchException e) {
            sc.nextLine();
            System.out.println("예외발생: 타입확인!");
            continue;
         }catch(Exception e) {
            System.out.println("예외발생: "+e.getMessage());
            continue;
         }
         break;
      }
}
	
	// (3)[아니요:2]의 경우 //act==2 
	public void downloadNo() {
		System.out.println();
		System.out.println("다운로드 하지 않았습니다.");
	}
	
	// (4)[예:1]의 경우 //act==1
	// 다운로드 한 게임의 추천여부 선택 메서드 
	public void recommend() {
		System.out.println();
		System.out.println("다운로드 시작합니다.");
		for(int i=0;i<5;i++) {
			System.out.println(".");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("다운로드 성공!"); //다운로드하면 추천여부 뜨게 하기위해 묶어버림
		
		while(true) {
		System.out.println();
		System.out.println("다운로드 한 게임을 추천하시겠습니까? [1.예 / 2.아니요]");
		System.out.print("숫자로 입력>>> ");	
        try {
            act=sc.nextInt();
            if(act<1 || act>2) {
               throw new Exception("범위외입력!");
            }
         }catch(InputMismatchException e) {
            sc.nextLine();
            System.out.println("예외발생: 타입확인!");
            continue;
         }catch(Exception e) {
            System.out.println("예외발생: "+e.getMessage());
            continue;
         }
         break;
      }
   }
	
	// (5)추천함  
	public void recommendYes() {
		System.out.println("해당 게임을 추천하였습니다.");
	}
	
	// (6)추천안함
	public void recommendNo() {
		System.out.println("해당 게임을 추천하지 않았습니다.");
	}
	
	// (7)  (게임다운o -> 추천여부 -> ) 지금까지 다운받은 게임리스트를 출력
	public void allDownloaded(ArrayList<GameDownVO> datas) {
		System.out.println();
		System.out.println("==내 게임 목록==");
		for(GameDownVO gdvo:datas) {
			System.out.println(gdvo);
		}
	}
	
// 3. 관리자 View-------------------------------------------
	
	public void adminView() { //관리자 id: admin, pw: 1234
		while(true) {
			System.out.println();
			System.out.println("※※NCGames-관리자모드※※");
			System.out.println("1.전체게임 목록"); 
			System.out.println("2.신규 게임 추가");
			System.out.println("3.기존 게임 삭제");
			System.out.println("4.게임 할인율 설정");
			System.out.println("5.게임 원가 설정");
			System.out.println("6.종료");
			System.out.print(">>> ");
			try {
				act=sc.nextInt();
				if(act<1 || act>6) {
					throw new Exception("1~6 사이의 정수를 입력하세요!");
				}
			}catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("예외발생: 타입확인!");
				continue;
			}catch(Exception e) {
				System.out.println("예외발생: "+e.getMessage());
				continue;
			}
			break;
		}
	}
	
	// (1)전체게임 목록 출력
	public void listAll(ArrayList<GameVO> datas) {
		System.out.println();
		System.out.println("===전체 게임목록===");
		for(GameVO gvo:datas) {
			System.out.println(gvo);
		}
	}

	// (2)신규 게임 추가
	public GameVO insertGame() {
		System.out.println();
		System.out.println("[신규 게임 추가]");
	
		System.out.print("추가할 게임이름: ");
		String gname =sc.next();		
				
		System.out.print("장르: ");
		String genre =sc.next();		
		
		int price;
		while(true) {
			System.out.print("가격(단위: 원): ");
			try {
				price=sc.nextInt();
				if(price<1000 || price>6000) {
					throw new Exception("1000~6000사이 정수를 입력해주세요!");
				}
			}catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("예외발생: 타입확인!");
				continue;
			}catch(Exception e) {
				System.out.println("예외발생: "+e.getMessage());
				continue;
			}
			break;	
		}
		
		int discount;
		while(true) {
		System.out.print("할인율(단위: %): ");
		try {
			discount= sc.nextInt();
			if(discount<10 || discount>90) {
				throw new Exception("10~90사이 정수를 입력해주세요!");
			}
		}catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("예외발생: 타입확인!");
			System.out.println();
			continue;
		}catch(Exception e) {
			System.out.println("예외발생: "+e.getMessage());
			System.out.println();
			continue;
		}
		break;
	}	
		GameVO gvo = new GameVO();
		gvo.setGname(gname);
		gvo.setGenre(genre);
		gvo.setPrice(price);
		gvo.setDiscount(discount);
		return gvo;
	} 					//[C]에서 gdao.insert(gvo) //생성한 게임 dao에 주기 (?)
	
	// (3) 기존 게임 삭제
	public GameVO deleteGame() {
		System.out.println();
		System.out.println("[기존 게임 삭제]");
		System.out.print("삭제할 게임번호 입력: ");
		int gnum=sc.nextInt();	
		
		GameVO gvo = new GameVO();
		gvo.setGnum(gnum);
		return gvo;
	}
	
	// (4) 게임 할인율 설정                //[day19-Test01참고]
	public GameVO updateDiscount() {
		System.out.println();
		System.out.println("[게임 할인율 설정]");
		System.out.print("게임번호 입력: ");
		int gnum=sc.nextInt();
		int discount;
		while(true) {
		System.out.println("할인율을 최소10%~최소90%로 설정해주세요");
		System.out.print("설정할 할인율 입력(단위: %): ");
		try {
			discount= sc.nextInt();
			if(discount<10 || discount>90) {
				throw new Exception("10~90사이 정수를 입력해주세요!");
			}
		}catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("예외발생: 타입확인!");
			System.out.println();
			continue;
		}catch(Exception e) {
			System.out.println("예외발생: "+e.getMessage());
			System.out.println();
			continue;
		}
		break;
	}	
		
		GameVO gvo = new GameVO();
		gvo.setGnum(gnum);
		gvo.setDiscount(discount);
		
		return gvo;
	}
	
	// (5) 게임 원가 설정
	public GameVO updatePrice() {
		System.out.println();
		System.out.println("[게임 원가 설정]");	
		System.out.println("게임번호 입력:");
		int gnum=sc.nextInt();
		int price;
		while(true) {
			System.out.println("원가를 최소1000원~최대6000원으로 설정해주세요");
			System.out.print("설정할 원가 입력(단위: 원): ");
			try {
				price=sc.nextInt();
				if(price<1000 || price>6000) {
					throw new Exception("1000~6000사이 정수를 입력해주세요!");
				}
			}catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("예외발생: 타입확인!");
				continue;
			}catch(Exception e) {
				System.out.println("예외발생: "+e.getMessage());
				continue;
			}
			break;	
		}
		
		GameVO gvo = new GameVO();
		gvo.setGnum(gnum);
		gvo.setPrice(price);
		
		return gvo;
	}	
	
	// (6) 관리자모드 종료 시
	// 수행 종료시 보여줄 메서드(관리자)
	public void adminProcessCancel() {
		System.out.println();
		System.out.println("관리자모드 종료합니다.");
		System.out.println("로그인 화면으로 이동합니다.");
	}

}
