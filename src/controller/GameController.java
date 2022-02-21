package controller;

import java.util.ArrayList;

import model.GameDAO;
import model.GameDataDAO;
import model.GameDownDAO;
import model.GameDownVO;
import model.GameVO;
import model.UserDAO;
import model.UserVO;
import view.GameView;

public class GameController {

	GameDAO gdao;
	GameDownDAO gddao;
	UserDAO udao;
	GameView view;
	GameDataDAO gdd;
	public GameController(){
		gdao = new GameDAO();
		gddao = new GameDownDAO();
		udao = new UserDAO();
		view = new GameView();
		gdd = new GameDataDAO();
		gdd.main();//크롤링
	}
	public void startApp() {
		while(true) {
			view.startView();
			if(view.act==1) {//로그인
				UserVO session = new UserVO();
				UserVO idone = new UserVO();
				String id = view.loginUserId();
				String pw=view.loginUserPw();
				
				
				idone.setUid(id);
				idone.setPwd(pw);
				session = udao.selectOneUser(idone); 
			

				if(session==null) {
					view.processFail();
					continue;
				}
				
				int unum = session.getUnum();
				
				


				if(unum<1000 && unum>0) {//관리자 모드

					while(true) {


						view.adminView();//수정할 게임 번호 입력받아옴


						if(view.act==1) {//1.전체 게임목록
							ArrayList<GameVO> datas = new ArrayList<GameVO>();
							GameVO vo = new GameVO();
							datas=gdao.selectAll(vo);
							view.listAll(datas);


						}else if(view.act==2) {//2. 게임추가
							GameVO data = new GameVO();
							data=view.insertGame();
							if(gdao.insertGame(data)) {
								view.processSuccess();
							}else {
								view.processFail();
							}
						}else if(view.act==3) {//3. 게임삭제
							GameVO data = new GameVO();
							data = view.deleteGame();//
							if(gdao.deleteGame(data)) {
								view.processSuccess();
							}else {
								view.processFail();							
							}
						}else if(view.act==4) {//4. 할인율수정 

							GameVO data = new GameVO();
							data = view.updateDiscount();
							if(gdao.updateDiscount(data)) {
								view.processSuccess();
							}else {
								view.processFail();							
							}
						}else if(view.act==5) {//5.원가수정
							GameVO data = new GameVO();
							data = view.updatePrice();
							if(gdao.updatePrice(data)) {
								view.processSuccess();
							}else {
								view.processFail();								
							}
						}else if(view.act==6) {//6. 종료
							view.adminProcessCancel();
							break;
						}

					}


				}else {//소비자 모드
					//구입리스트가 없을 시 할인율 순 -디폴트
					//구입리스트가 있을 시 장르 추천순 *구현예정*
					ArrayList<GameVO> datas = new ArrayList<GameVO>();
					GameVO vo = new GameVO();
					datas=gdao.selectAllDiscount(vo);
					view.listDiscount(datas);


					while(true) {
						view.clientView();
						ArrayList<GameVO> gdatas = new ArrayList<GameVO>();
						if(view.act ==1) {//1. 장르순, 추천순
							//GameVO data = new GameVO();
							view.listGenre();
							GameVO gvo=null;
							if(view.act==1) {//액션
								gvo = new GameVO();
								gvo.setGenre("Action");
								gdatas=gdao.selectGenre(gvo);
								view.action(gdatas);
							}else if(view.act==2) {//어드벤쳐
								gvo = new GameVO();
								gvo.setGenre("Adventure");
								gdatas=gdao.selectGenre(gvo);
								view.adventure(gdatas);
							}else if(view.act==3) {//캐쥬얼
								gvo = new GameVO();
								gvo.setGenre("Casual");
								gdatas=gdao.selectGenre(gvo);
								view.casual(gdatas);
							}else if(view.act==4) {//소설
								gvo = new GameVO();
								gvo.setGenre("Novel");
								gdatas=gdao.selectGenre(gvo);
								view.novel(gdatas);
							}else if(view.act==5) {//레이싱
								gvo = new GameVO();
								gvo.setGenre("Racing");
								gdatas=gdao.selectGenre(gvo);
								view.racing(gdatas);
							}else if(view.act==6) {//RPG
								gvo = new GameVO();
								gvo.setGenre("RPG");
								gdatas=gdao.selectGenre(gvo);
								view.rpg(gdatas);
							}else if(view.act==7) {//시뮬레이션
								gvo = new GameVO();
								gvo.setGenre("Simulation");
								gdatas=gdao.selectGenre(gvo);
								view.simulation(gdatas);
							}



						}
						/*else if(view.act==2) {//2. 출시일  
						 * //구현예정
							GameVO data = new GameVO();
							datas = gdao.selectAllDate(data);
							view.listDate(datas);
						}*/
						else if(view.act ==2) {//3. 추천순
							GameVO data = new GameVO();
							datas = gdao.selectAllRcnt(data);
							view.listRcnt(datas);
						}
						else if(view.act ==3) {//4. 가격순
							GameVO data = new GameVO();
							datas = gdao.selectAllPrice(data);
							view.listPrice(datas);	
						}
						else if(view.act==4) {//5. 할인율
							GameVO data = new GameVO();
							datas=gdao.selectAllDiscount(data);
							view.listDiscount(datas);
						}
						else if(view.act ==5) {//6. 종료
							break;//종료
						}

						view.choose();
						if(view.act==2) {
							continue;//고객 선택페이지로 이동
						}else if(view.act==1) {
							GameVO data;
							while(true) {
								data = new GameVO();
								int num = view.selectGame();//게임번호 받음


								data.setGnum(num);//listGenre에서 다운받을 게임번호를 입력받아 set
								data = gdao.selectOne(data);
								if(data == null) {
									view.processFail();
									continue;
								}
								break;
							}

							view.download(data);
							if(view.act==1){//게임정보를 그대로 인풋
								//다운한다면
								gdao.download(data);//게임 다운수 1증가
							}else {
								//다운하지 않는다면
								view.downloadNo();
								continue;//고객 선택페이지로 이동
							}

							//유저번호, 게임번호, 추천여부(1,0)을 저장하여 게임다운 리드스트 저장
							GameDownVO ddata = new GameDownVO();
							ddata.setUnum(session.getUnum());
							ddata.setGnum(data.getGnum());
							view.recommend();
							String to="";
							if(view.act==1) {
								//추천한다면 추천 여부=1
								to = Integer.toString(1);
								view.recommendYes();

							}else if(view.act==2) {
								//추천하지않는다면 추천 여부=0
								to = Integer.toString(0);
								view.recommendNo();

							}
							ddata.setReYN(to);
							gddao.insertDown(ddata);

							//게임 추천수 업데이트 메서드
							gdao.recommend(data);
							ArrayList<GameDownVO> ddatas =new ArrayList<GameDownVO>();
							ddatas=gddao.selectUser(ddata);//gameDownDAO에서 유저의 다운리스트 받아옴
							view.allDownloaded(ddatas);//유저 다운로드 리스트 출력
						}

					}
					//break;//전체 종료
				}

			}else if(view.act ==2) {//회원가입
				UserVO vo = new UserVO(); 
				String id=view.insertUserId();
				String pw=view.insertUserPw();
				
				vo.setUid(id);
				vo.setPwd(pw);
				if(udao.insertUser(vo)) {
					view.processSuccess();
				}else {
					view.processFail();
				}

			}else if(view.act==3) {//전체종료종료
				view.processCancel();
				break;
			}
			//String id = view
		}
	}
}
