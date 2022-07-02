import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MyLibrary
{
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	Scanner sc = new Scanner(System.in);
	
	Connection con = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	PreparedStatement pstmt5 = null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	PreparedStatement pstmt8 = null;
	PreparedStatement pstmt9 = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	
	public void connectDatabase()
	{
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void primaryMenu()
	{
		System.out.println("");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ ");
		System.out.println("┃ ㄲ ㅐ ㉥ㅣ 책방을 찾아주셔서 감사합니다. ┃ ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
		System.out.println("");
		System.out.println("        원하는 항목을 선택해주세요.");
		System.out.println("            ┏━━━━━━━━━━━━━━━━━┓     ");
		System.out.println("            ┃  1.회원 관리    ┃     "); 
		System.out.println("            ┃  2.책           ┃     ");
		System.out.println("            ┃  3.대여 / 반납  ┃     ");
		System.out.println("            ┃  4.가격안내     ┃     ");
		System.out.println("            ┃  0.종료         ┃     ");
		System.out.println("            ┗━━━━━━━━━━━━━━━━━┛     ");
		System.out.println("     이외의 번호를 누르시면 종료됩니다");
		System.out.println("      ");
		System.out.print("                  선택 : ");
		
	}
	public void memberMenu()
	{
		System.out.println("");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ ");
		System.out.println("┃                회원 관리                 ┃ ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
		System.out.println("");
		System.out.println("        원하는 항목을 선택해주세요. ");
		System.out.println("            ┏━━━━━━━━━━━━━━━━━┓     ");
		System.out.println("            ┃  1.회원 가입    ┃     "); 
		System.out.println("            ┃  2.회원정보조회 ┃     ");
		System.out.println("            ┃  3.선불금액충전 ┃     ");
		System.out.println("            ┃  4.연체자목록   ┃     ");
		System.out.println("            ┃  5.회원탈퇴     ┃     ");
		System.out.println("            ┃  0.뒤로가기     ┃     ");
		System.out.println("            ┗━━━━━━━━━━━━━━━━━┛     ");
		System.out.println("    이외의 번호를 누르시면 뒤로가집니다.");
		System.out.println("      ");
		System.out.print("                  선택 : ");
	}
	public void bookMenu()
	{
		System.out.println("");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ ");
		System.out.println("┃                   책                     ┃ ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
		System.out.println("");
		System.out.println("        원하는 항목을 선택해주세요.");
		System.out.println("            ┏━━━━━━━━━━━━━━━━━┓     ");
		System.out.println("            ┃  1.책등록       ┃     "); 
		System.out.println("            ┃  2.책 조회      ┃     ");
		System.out.println("            ┃  3.책 폐기      ┃     ");
		System.out.println("            ┃  4.책 전체 조회 ┃     ");
		System.out.println("            ┃  0.뒤로가기     ┃     ");
		System.out.println("            ┗━━━━━━━━━━━━━━━━━┛     ");
		System.out.println("    이외의 번호를 누르시면 뒤로가집니다.");
		System.out.println("      ");
		System.out.print("                  선택 : ");
	}
	public void rentMenu()
	{
		System.out.println("");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ ");
		System.out.println("┃               대여 / 반납                ┃ ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
		System.out.println("");
		System.out.println("        원하는 항목을 선택해주세요.");
		System.out.println("            ┏━━━━━━━━━━━━━━━━━┓     ");
		System.out.println("            ┃  1.책 대여      ┃     "); 
		System.out.println("            ┃  2.책 반납      ┃     ");
		System.out.println("            ┃  3.책 연장      ┃     ");
		System.out.println("            ┃  4.책 조회      ┃     ");
		System.out.println("            ┃  5.선불금액조회 ┃     ");
		System.out.println("            ┃  6.회원연체조회 ┃     ");
		System.out.println("            ┃  0.뒤로가기     ┃     ");
		System.out.println("            ┗━━━━━━━━━━━━━━━━━┛     ");
		System.out.println("    이외의 번호를 누르시면 뒤로가집니다.");
		System.out.println("      ");
		System.out.print("                  선택 : ");
	}
	public void costMenu()
	{
		System.out.println("");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ ");
		System.out.println("┃               대여비용안내               ┃ ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
		System.out.println("");
		System.out.println("");
		System.out.println("                 <가입혜택> ");
		System.out.println("        처음 가입시 1000원을 드립니다.   ");
		System.out.println(""); 
		System.out.println("               <선불요금 충전>");
		System.out.println("        5,000원 이상 충전시 10% 보너스");
		System.out.println("       10,000원 이상 충전시 15% 보너스");
		System.out.println("       20,000원 이상 충전시 20% 보너스");
		System.out.println("       30,000원 이상 충전시 25% 보너스");
		System.out.println("       50,000원 이상 충전시 30% 보너스");
		System.out.println("      100,000원 이상 충전시 50% 보너스");
		System.out.println("");
		System.out.println("                  <대여비>");
		System.out.println("         만화책 : 500원 (7일 기준)");
		System.out.println("         소설   : 700원 (7일 기준)");
		System.out.println("");
		System.out.println("                  <연체료>");
		System.out.println("              1일 연체시 100원");
		System.out.println("");
		System.out.println("초기화면으로 돌아가시려면 엔터를 누르세요.");
		String enter = sc.nextLine();
	}
	public void addMember()
	{
		while(true)
		{	
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			
			String sql1 = "select * from memberinfo "
					+ "where member_id = ?";
			if(id.equals(""))
			{
				System.out.println("ID를 입력하지않으셨습니다. 처음부터 다시 입력해주세요.");
				continue;
			}
			try
			{
				pstmt2 = con.prepareStatement(sql1);
				pstmt2.setString(1, id);
				rs = pstmt2.executeQuery();
				if(rs.next())
				{
					System.out.println("중복된 아이디입니다!! 다시입력해주세요. ");
					continue;
				}
			}
			catch (Exception e)
			{
				System.out.println("");
				continue;
			}
			
			System.out.print("이름 : ");
			String name = sc.nextLine();
			if(name.equals(""))
			{
				System.out.println("이름은 입력하지않으셨습니다. 처음부터 다시 입력해주세요.");
				continue;
			}
			System.out.print("휴대폰번호 : ");
			String phoneNumber = sc.nextLine();
			System.out.println("주소 : ");
			String address = sc.nextLine();
		
			String sql2 = "insert into memberinfo(member_number, member_id, member_name, black_member, phonenumber, address,money) "
					                   + "values(memberinfo_seq.nextval, ?, ?, 'false', ?, ?, 1000)";
			try
			{
				pstmt1 = con.prepareStatement(sql2);
				pstmt1.setString(1, id);
				pstmt1.setString(2, name);
				pstmt1.setString(3, phoneNumber);
				pstmt1.setString(4, address);
				pstmt1.executeUpdate();
				System.out.println("회원등록 되었습니다.");
				
			}
			catch (Exception e)
			{
				System.out.println();
			}
			
			System.out.println("이름        : "+name);
			System.out.println("ID          : "+id);
			System.out.println("휴대폰 번호 : "+phoneNumber);
			System.out.println("주소        : "+address);
			
			break;
		}
	}
	public void rentBook()
	{
		while(true)
		{
			try
			{
				System.out.println("대여하실분의 ID를 입력해주세요. ");
				String id = sc.nextLine();
				
				String sql1 = "select member_name, black_member from memberinfo where member_id = ?";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, id);
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next())
				{
					if(rs.getString(2).equals("true"))
					{
						System.out.println("연체자입니다. 대여할 수 없습니다. 반납을 먼저 해주세요. ");
						return;
					}
					System.out.println(rs.getString(1)+" 님 대여하실 책 제목을 입력해주세요." );
				}
				else
				{
					System.out.println("ID가 존재하지않습니다. 다시입력해주세요.");
					continue;
				}
				
				String sql9 = "select count(*) from rentalinfo where member_id = ?";
				pstmt9 = con.prepareStatement(sql9);
				pstmt9.setString(1, id);
				rs1=pstmt9.executeQuery();
				if(rs1.next())
				{
					if(rs1.getInt(1)>20)
					{
						System.out.println("20권 이상은 빌릴 수 없습니다. 초기화면으로 돌아갑니다.");
						return;
					}
				}
				
				String title;
				while(true)
				{
					title = sc.nextLine();
					
					String sql2 = "select book_title, book_Number from bookinfo where book_stock=1 and book_title like ? order by book_number";
					
					
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setString(1, "%"+title+"%");
					ResultSet rs3 = pstmt2.executeQuery();
					if(rs3.next())
					{
						do
						{
							System.out.print("책 제목 : " + rs3.getString(1)+"\t");
							System.out.println("책 번호 : " + rs3.getString(2));
						}while(rs3.next());
						break;
					}
					else
					{
						System.out.println("책방에 존재하지않는 책입니다. ");
						System.out.println("다시 책제목을 입력해주세요.");
						continue;
					}
				}
				
				
				System.out.println("대여하실 책 번호를 입력해주세요.");
				String bookNumber = sc.nextLine();
				
				String sql6 = "select money from memberinfo "
				               + "where money >= (select borrowprice from bookinfo where book_number = ?) "
				               + "and member_id = ? ";
				pstmt6 = con.prepareStatement(sql6);
				pstmt6.setString(1, bookNumber);
				pstmt6.setString(2, id);
				rs = pstmt6.executeQuery();
				if(rs.next()==false)
				{
					System.out.println("잔액이 부족합니다. 잔액 충전 후 대여 부탁드립니다. ");
					return;
				}
				
				
				String sql3 = "insert into rentalinfo \r\n"
						+ " values ( (select book_number from bookinfo where book_number= ?), "
						+ "         (select book_title from bookinfo where book_number= ?), "
						+ "         (select member_id from memberinfo where member_id = ?), "
						+ "         (select member_name from memberinfo where member_id = ?), "
						+ "         sysdate,sysdate+7)";
				pstmt3 = con.prepareStatement(sql3);
				pstmt3.setString(1, bookNumber);
				pstmt3.setString(2, bookNumber);
				pstmt3.setString(3, id);
				pstmt3.setString(4, id);
				pstmt3.executeUpdate();
				
				String sql4 = "update bookinfo set book_stock= 0  where book_number = ? ";
				pstmt4 = con.prepareStatement(sql4);
				pstmt4.setString(1, bookNumber);
				pstmt4.executeUpdate();
				
				String sql5 = "select book_title, book_number, rental_day,return_day "
						+ " from rentalinfo "
						+ " where book_number= ? ";
				
				pstmt5 = con.prepareStatement(sql5);
				pstmt5.setString(1, bookNumber);
				ResultSet rs1 = pstmt5.executeQuery();
				
				while(rs1.next())
				{
					System.out.print("책 제목 : " + rs1.getString(1)+"\t");
					System.out.println("책 번호 : " + rs1.getString(2));
					System.out.println("대여일 : " + rs1.getString(3).substring(0, 10));
					System.out.println("반납일 : " + rs1.getString(4).substring(0, 10));
				}
				
				String sql7 = "update memberinfo "
					        	+ "set money = (money-(select borrowprice from bookinfo where book_number = ?)) "
						        + "where member_id = ?";
				pstmt7 = con.prepareStatement(sql7);
				pstmt7.setString(1, bookNumber);
				pstmt7.setString(2, id);
				pstmt7.executeUpdate();
				
				String sql8 = "select member_name, money from memberinfo where member_id = ?";
				
				pstmt8 = con.prepareStatement(sql8);
				pstmt8.setString(1, id);
				rs = pstmt8.executeQuery();
				if(rs.next())
				{
					System.out.println(rs.getString(1) + "님의 남은 잔액은 "+ rs.getString(2)+"원 입니다. ");
				}
				
				System.out.println("책이 대여되었습니다. 감사합니다.");
				rs1.close();
				rs.close();
				break;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("데이터베이스 입력 에러입니다.");
			}
		}
	}
	public void returnBook()
	{
		while(true)
		{
			try
			{ 
				System.out.println("반납하실 책 제목을 입력해주세요.");
				String title = sc.nextLine();
				
				String sql1 = "select book_title, book_Number, member_name "
									+ "from rentalinfo "
									+ "where book_title like ?";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, "%"+title+"%");
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next())
				{
					do
					{
						System.out.print("대여자 : " + rs.getString(3)+"\t");
						System.out.print("책 제목 : " + rs.getString(1)+"\t");
						System.out.println("책 번호 : " + rs.getString(2));
					}while(rs.next());
				}
				else
				{
					System.out.println("대여중인 책이 아닙니다.");
					continue;
				}
				String bookNumber;
				
				while(true)
				{
					System.out.println("반납하실 책번호를 입력해주세요.");
					bookNumber = sc.nextLine();
					
					String sql5 = "select member_id from rentalinfo "
										+ "where book_number = ?";
					pstmt5 = con.prepareStatement(sql5);
					pstmt5.setString(1, bookNumber);
					ResultSet rs1 = pstmt5.executeQuery();
				
					String sql6 = "select black_member, money from memberinfo where member_id= ?";
					if(rs1.next())
					{
						pstmt4 = con.prepareStatement(sql6);
						pstmt4.setString(1, rs1.getString(1));
						rs2= pstmt4.executeQuery();
						if(rs2.next())
						{
							if(rs2.getString(1).equals("true"))   /////블랙리스트일경우 (연체료만큼)돈을 까라
							{
								String sql2 = "select book_title, trunc(sysdate)-trunc(return_day) "
										+ "from rentalinfo "
										+ "where member_id = ? and (trunc(sysdate)-trunc(return_day))>0 "
										+ "and book_number = ? ";
								pstmt2 = con.prepareStatement(sql2);
								pstmt2.setString(1, rs1.getString(1));
								pstmt2.setString(2, bookNumber);
								rs = pstmt2.executeQuery();
								if(rs.next())
								{
									System.out.println("연체된 책입니다. 회원님");
									System.out.println();
									System.out.print("연체된 책 : " + rs.getString(1) +"\t");
									System.out.print("연체일수 : "+ rs.getInt(2) +"일"+"\t");
									System.out.println("연체료 : " + (rs.getInt(2)*100) +"원");
								}
								
								System.out.println(rs1.getString(1) +" 회원님 남은잔액 " +rs2.getInt(2) +"원에서 " + "연체료 " + (rs.getInt(2)*100) +"원을 차감합니다");
								
								String sql4 = "select * from memberinfo where money >= ? and member_id = ?";
								pstmt4 = con.prepareStatement(sql4);
								pstmt4.setInt(1, (rs.getInt(2)*100));
								pstmt4.setString(2, rs1.getString(1));
								ResultSet rs4 = pstmt4.executeQuery();
								
								if(rs4.next()==false)
								{
									System.out.println("남은 잔액이 부족합니다. 선불요금을 충전 후 반납해주세요. ");
									return;
								}
								
								String sql3 = "update memberinfo set money = money-? where member_id= ?";
								pstmt3 = con.prepareStatement(sql3);
								pstmt3.setInt(1, (rs.getInt(2)*100));
								pstmt3.setString(2, rs1.getString(1));
								pstmt3.executeUpdate();
							}
							else								/////블랙리스트 아닐경우 돈을 까지마라 걍 고 
							{
								break;
							}
						}
						break;
					}
					else
					{
						System.out.println("없는 책번호입니다. 다시입력해주세요.  ");
						continue;
					}
				}
				
				String sql7 = "select member_id from rentalinfo "
						+ "where book_number = ?";
				pstmt5 = con.prepareStatement(sql7);
				pstmt5.setString(1, bookNumber);
				rs1 = pstmt5.executeQuery();
			
				String sql4 = "update memberinfo "
						+ " set black_member='false' "
						+ " where member_id = ?";
				if(rs1.next())
				{
					pstmt4 = con.prepareStatement(sql4);
					pstmt4.setString(1, rs1.getString(1));
					pstmt4.executeUpdate();
				}
				
				String sql2 = "delete from rentalinfo "
						+ "where book_number = ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, bookNumber);
				pstmt2.executeUpdate();
				
				String sql3 = "update bookinfo set book_stock = 1  where book_number = ? ";
				pstmt3 = con.prepareStatement(sql3);
				pstmt3.setString(1, bookNumber);
				pstmt3.executeUpdate();		
				
				System.out.println("반납되셨습니다.");
				rs.close();
				rs1.close();
				rs2.close();
				break;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("데이터베이스 입력 에러입니다.");
			}
		}
	
	}
	public void setBook()
	{
		System.out.print("등록하실 책 제목을 입력해주세요. ");
		String title = sc.nextLine();
		System.out.print("책의 종류를 입력하세요. ");
		String categorize = sc.nextLine();
		System.out.print("대여가격을 입력하세요. ");
		String borrowprice = sc.nextLine();
		
		String sql = "insert into bookinfo(book_number, book_title, book_stock,categorize, borrowprice)"
				              + " values(bookinfo_seq.nextval ,? , 1, ?, ?)";
		try
		{
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, title);
			pstmt1.setString(2, categorize);
			pstmt1.setString(3, borrowprice);
			pstmt1.executeUpdate();
			System.out.println("책등록이 완료되었습니다.");
		}
		catch (Exception e)
		{
			System.out.println();
			System.out.println("책 제목을 입력하지않으셨습니다. 초기화면으로 돌아갑니다.");
		}
	}
	public void askBook()
	{
		try
		{	
			while(true)
			{
				System.out.print("조회하실 책 제목을 입력해주세요.  ");
				String title =sc.nextLine();
			
				String sql = "select book_title,count(book_title) from bookinfo where book_stock = 1 and  book_title like ? group by book_title order by book_title";
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, "%"+title+"%");
				ResultSet rs = pstmt1.executeQuery();
				String sql1 = "select book_title,count(book_title) from bookinfo where book_title like ? group by book_title order by book_title";
				pstmt2 = con.prepareStatement(sql1);
				pstmt2.setString(1, "%"+title+"%");
				ResultSet rs1 = pstmt2.executeQuery();
				if(rs.next())
				{
					do
					{
						System.out.print("책 제목 : " + rs.getString(1)+"\t");
						System.out.print("대여 가능 : "+ rs.getString(2)+"\t");	
						if(rs1.next())
						{
							System.out.println("총 수량 : " + rs1.getString(2));
						}
						else
						{
							System.out.println("총수량 : 0" );
						}
					}while(rs.next());
					break;
				}
				else
				{
					System.out.println("조회하신책이 없습니다. 다시입력해주세요.");
					System.out.println("다시 조회하시려면 【1】을 눌러주세요.");
					System.out.println("다른 번호를 누르시면 초기화면으로 돌아갑니다. ");
					int choice;
					choice = sc.nextInt();
					sc.nextLine();
					if(choice==1)
					{
						continue;
					}
					else
					{
						rs.close();
					}	
				}
				
				rs.close();
				rs1.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	public void askAllBook()
	{
		String sql = "select book_title,count(book_title),categorize,borrowprice "
				+ "from bookinfo "
				+ "group by book_title,categorize,borrowprice "
				+ "order by book_title, categorize";
		try
		{
			pstmt1 = con.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next())
			{
				System.out.print("책 제목 : " + rs.getString(1)+"\t");
				System.out.print("수 량 : "+ rs.getString(2) +"\t");
				System.out.print("종 류 : "+ rs.getString(3) +"\t");
				System.out.println("대여비 : "+ rs.getString(4)+"원");
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
	}
	public void byeBook()
	{
		for(int i = 0 ; i<1 ; i++)
		{
			System.out.println("삭제할 책 제목 : ");
			String title = sc.nextLine();
			
			String sql1 = "select book_title, book_Number, book_stock from bookinfo where book_title like ? order by book_number";
			try
			{
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, "%"+title+"%");
				ResultSet rs = pstmt1.executeQuery();
			
				if(rs.next())
				{
					do 
					{
						System.out.print("책 제목 : " + rs.getString(1)+"\t");
						System.out.print("책 번호 : " + rs.getString(2)+"\t");
						if(rs.getString(3).equals("1")) { System.out.println("폐기 가능"); }
						else                            { System.out.println("대여중"  ); }
					}
					while(rs.next());
				}
				else
				{
					System.out.println("※ 책방에 없는 책입니다.");
					System.out.println("※ 다시 입력해주세요.");
					System.out.println();
					rs.close();
					i--;
					continue;
				}
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("데이터베이스 입력 에러입니다.");
			}
		}
		try
		{
			System.out.println("삭제할 책 번호를 입력해주세요");
			String bookNumber = sc.nextLine();
			
			String sql2 = "delete from bookinfo where book_number = ?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, bookNumber);
			pstmt2.executeUpdate();
			System.out.println("삭제되었습니다.");
		}
		catch (Exception e)
		{
			System.out.println("대여중인 책 입니다. 삭제할 수 없습니다.");
		}	
	}
	public void blackListReset()
	{
		String sql1 = "select member_id "
				       + " from rentalinfo "
				       + " where trunc(sysdate) > trunc(return_day)";
		
		String sql2 = "update memberinfo "
				        + " set black_member='true' "
				        + " where member_id = ?";
		try
		{
			pstmt1 = con.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next())
			{
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setString(1, rs.getString(1));
					pstmt2.executeUpdate();
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}	
		
		String sql3 = "select member_id "
			       + " from rentalinfo "
			       + " where trunc(sysdate) <= trunc(return_day)";
	
		String sql4 = "update memberinfo "
				        + " set black_member='false' "
				        + " where member_id = ?";
		try
		{
			pstmt1 = con.prepareStatement(sql3);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next())
			{
					pstmt2 = con.prepareStatement(sql4);
					pstmt2.setString(1, rs.getString(1));
					pstmt2.executeUpdate();
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	public void blackList()
	{
		String sql1 = "select member_name, member_id from memberinfo "
				+ "where black_member = 'true' order by member_name";
		String sql2 = "select book_title, trunc(sysdate)-trunc(return_day) "
				+ "from rentalinfo "
				+ "where member_id = ? and (trunc(sysdate)-trunc(return_day))>0";
		try
		{
			pstmt1 = con.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			
			if(rs.next())
			{
				do
				{
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setString(1, rs.getString(2));
					ResultSet rs1 = pstmt2.executeQuery();
					while(rs1.next())
					{
						System.out.print("블랙리스트 이름 : " + rs.getString(1)+"\t");
						System.out.print("연체된 책 : " + rs1.getString(1) +"\t");
						System.out.print("연체일수 : "+ rs1.getInt(2) +"일"+"\t");
						System.out.println("연체료 : " + (rs1.getInt(2)*100) +"원");
					}
					rs1.close();
				}while(rs.next());
			}
			else
			{
				System.out.println("연체한 사람이 없습니다. ");
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	public void addReturnDate()
	{
		String id;
		
		while(true)
		{
			System.out.println("id를 입력해주세요");
			id = sc.nextLine();
			
			try
			{
				String sql = "select black_member from memberinfo where member_id = ?";
				
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setString(1, id);
				rs1 = pstmt3.executeQuery();
				if(rs1.next())
				{
					if(rs1.getString(1).equals("true"))
					{
						System.out.println("연체자입니다. 책 연장이 불가능합니다. 반납을 먼저해주세요.");
						return;
					}
				}
			
				String sql1 = "select book_title, book_number, rental_day,return_day "
					+ " from rentalinfo "
					+ " where member_id = ?";
			
				pstmt2 = con.prepareStatement(sql1);
				pstmt2.setString(1, id);
				ResultSet rs = pstmt2.executeQuery();
				
				if(rs.next())
				{
					System.out.println("< 빌린 책 목록 >");
					do
					{
						System.out.print("책 제목 : " + rs.getString(1)+"\t");
						System.out.println("책 번호 : " + rs.getString(2));
						System.out.println("대여일 : " + rs.getString(3).substring(0, 10));
						System.out.println("반납일 : " + rs.getString(4).substring(0, 10));
					}while(rs.next());
					rs.close();
					break;
				}
				else
				{
					System.out.println("없는 ID입니다.");
					continue;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("데이터베이스 입력 에러입니다.");
			}
		}
		
		System.out.println("7일 연장에 300원입니다. ");
		System.out.println("7일 연장하실 책 번호를 입력해주세요 ");
		String bookNumber = sc.nextLine();
	
		String sql3 = "select money from memberinfo where money>=300 and member_id=?";
		
		try
		{
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, id);
			rs = pstmt3.executeQuery();
			if(rs.next()==false)
			{
				System.out.println("연장하실 금액이 부족합니다. 선불요금을 충전후 시도해주세요.");
				return;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
		
		
		String sql2 = "update rentalinfo "
				+ " set return_day = trunc(return_day)+7 "
				+ " where book_number= ?";
		
		String sql4 = "update memberinfo set money=money-300 where member_id = ?";
		
		try
		{
			pstmt1 = con.prepareStatement(sql2);
			pstmt1.setString(1, bookNumber);
			pstmt1.executeUpdate();
			System.out.println("연장 되었습니다. ");
			
			pstmt3 = con.prepareStatement(sql4);
			pstmt3.setString(1, id);
			pstmt3.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	public void askMember()
	{
		try
		{
			System.out.println("이름를 입력해주세요");
			String memberName = sc.nextLine();
			
			String sql= "select member_name, member_id from memberinfo "
					+ "where member_name like ?";
			
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, "%"+memberName+"%");
			rs = pstmt1.executeQuery();
		
			if(rs.next())
			{
				do
				{
					System.out.print("이름 : " + rs.getString(1)+"\t");
					System.out.println("ID : " + rs.getString(2));
				}
				while(rs.next());
			}
			else
			{
				System.out.println("존재하지않는 이름입니다. 초기화면으로 돌아갑니다. ");
				return;
			}
			
			System.out.println("회원 ID를 입력해주세요.");
			String id = sc.nextLine();
			
			String sql1= "select * from memberinfo "
					         + "where member_id = ?";
			
			pstmt2 = con.prepareStatement(sql1);
			pstmt2.setString(1, id);
			rs1 = pstmt2.executeQuery();
			if(rs1.next())
			{
				do
				{
					System.out.println("회원번호 : " + rs1.getString(1));
					System.out.println("ID : " + rs1.getString(2));
					System.out.println("이름 : " + rs1.getString(3));
					
					if(rs1.getString(4).equals("true"))
					{
						System.out.println("연체자여부 : 연체자 ");
					}
					else if(rs1.getString(4).equals("falase"))
					{
						System.out.println("연체자여부 : 연체자가 아닙니다.");
					}
					System.out.println("휴대폰번호 : " + rs1.getString(5));
					System.out.println("주소 : " + rs1.getString(6));
					System.out.println("선불요금 : " + rs1.getString(7) +" 원");
				}
				while(rs1.next());
			}
			else
			{
				System.out.println("존재하지않는 ID입니다. 초기화면으로 돌아갑니다. ");
				return;
			}
			rs.close();
			rs1.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	public void chargeMoney()
	{
		try
		{
			System.out.println("회원 ID를 입력해주세요.");
			String id = sc.nextLine();
			
			String sql= "select member_name, money from memberinfo "
					         + "where member_id = ?";
			
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			rs = pstmt1.executeQuery();
			if(rs.next())
			{
				System.out.println(rs.getString(1) +" 회원님의 남은 잔액은 "+ rs.getString(2) + " 원 입니다.");
				System.out.println();
			}
			else
			{
				System.out.println("존재하지않는 ID입니다. 초기화면으로 돌아갑니다. ");
				return;
			}
			
			System.out.println("얼마를 충전하시겠습니까?");
			int money = sc.nextInt();
			sc.nextLine();
			
			String sql1 = "update memberinfo "
					+ " set money = money + ? "
					+ " where member_id = ? ";
			if(money>=100000)
				money=(int) (money*1.5);
			else if(money>=50000)
				money=(int) (money*1.3);
			else if(money>=30000)
				money=(int) (money*1.25);
			else if(money>=20000)
				money=(int) (money*1.2);
			else if(money>=10000)
				money=(int) (money*1.15);
			else if(money>=5000)
				money=(int) (money*1.1);
				
			pstmt2 = con.prepareStatement(sql1);
			pstmt2.setInt(1, money);
			pstmt2.setString(2, id);
			pstmt2.executeUpdate();
			
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			rs = pstmt1.executeQuery();
			if(rs.next())
			{
				System.out.println("충전완료되었습니다.");
				System.out.println("회원님의 남은 잔액은 "+ rs.getString(2) + " 원 입니다.");
				System.out.println();
			}
			rs.close();
		}
		catch (Exception e)
		{
			System.out.println("충전하실 금액을 입력해주세요. (숫자만입력해주세요) ");
		}
	}
	public void leaveMember()
	{
		try
		{
			System.out.println("회원 ID를 입력해주세요.");
			String id = sc.nextLine();
			
			String sql1 = "select member_id from memberinfo where member_id = ?";
			pstmt2 = con.prepareStatement(sql1);
			pstmt2.setString(1, id);
			rs = pstmt2.executeQuery();
			if(rs.next()==false)
			{
				System.out.println(id + "는존재하지않는 id 입니다. 초기화면으로 돌아갑니다.");
				return;
			}
			
			System.out.println("탈퇴하시려면 이름을 정자로 입력해주세요.");
			String memberName = sc.nextLine();
			
			System.out.println("");
			System.out.println("진짜 탈퇴하시려면 Y를 눌러주세요.");
			System.out.println("탈퇴하고싶지 않으시면 아무키나 누르고 엔터를 쳐주세요.");
			
			String sign = sc.nextLine();
			
			if(sign.equalsIgnoreCase("Y"))
			{
				String sql = "delete from memberinfo "
						+ " where member_id = ? ";
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, id);
				pstmt1.executeUpdate();
				System.out.println(memberName+ " 님 탈퇴되었습니다. ");
			}
			else
			{
				System.out.println(memberName+ " 님 다시 돌아와주셔서 감사합니다.  ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	public void askMoney()
	{
		try
		{
			while(true)
			{	
				System.out.println("회원 ID를 입력해주세요.");
				String id = sc.nextLine();
				
				String sql= "select member_name, money from memberinfo "
						         + "where member_id = ?";
				
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, id);
				rs = pstmt1.executeQuery();
				if(rs.next())
				{
					System.out.println(rs.getString(1)+ " 회원님의 남은 잔액은 "+ rs.getString(2) + " 원 입니다.");
					System.out.println();
					rs.close();
					break;
				}
				else
				{
					System.out.println("없는 회원입니다. 다시 ID를 입력해주세요.");
					continue;
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
		
	}
	public void askBlackList()
	{
		try
		{
			System.out.println("회원 ID를 입력해주세요.");
			String id = sc.nextLine();
			
			String sql = "select member_name, black_member from memberinfo "
					          + "where member_id = ?";
			
			
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			rs = pstmt1.executeQuery();
			
			if(rs.next())
			{
				if(rs.getString(2).equals("false"))
				{
					System.out.println(rs.getString(1)+ " 님은 연체자가 아닙니다. ");
				}
				else if(rs.getString(2).equals("true"))
				{
					System.out.println(rs.getString(1)+ " 님은 연체자입니다. ");
					
					String sql2 = "select book_title, trunc(sysdate)-return_day "
							+ "from rentalinfo "
							+ "where member_id = ? and (trunc(sysdate)-return_day)>0";
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setString(1, id);
					ResultSet rs1 = pstmt2.executeQuery();
					while(rs1.next())
					{
						System.out.print("연체된 책 : " + rs1.getString(1) +"\t");
						System.out.println("연체일수 : "+ rs1.getString(2) +"일");
					}
					rs1.close();
				}				
			}
			else
			{
				System.out.println("존재하지 않는 ID입니다. 초기화면으로 돌아갑니다.");
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
    public void doRun()
	{
		connectDatabase();
		int choice1;
		int choice2;
		while(true) {
			primaryMenu();
			blackListReset();
			choice1 = sc.nextInt();
			sc.nextLine();
			switch(choice1) {
			case 1:
				memberMenu();
				choice2 = sc.nextInt();
				sc.nextLine();
				switch(choice2) {
				case 1: addMember();   break;
				case 2: askMember();   break;
				case 3: chargeMoney(); break;
				case 4: blackList();   break;
				case 5: leaveMember(); break;
				default:               break; } break;
			case 2:
				bookMenu();
				choice2 = sc.nextInt();
				sc.nextLine();
				switch(choice2) {
				case 1: setBook();     break;
				case 2: askBook();     break;
				case 3: byeBook();     break;
				case 4: askAllBook();  break;
				default:               break; } break;
			case 3:
				rentMenu();
				choice2 = sc.nextInt();
				sc.nextLine();
				switch(choice2) {
				case 1: rentBook();       break;
				case 2: returnBook();     break;
				case 3: addReturnDate();  break;
				case 4: askBook();        break;
				case 5: askMoney();       break;
				case 6: askBlackList();   break;
				default:                  break; } break;
			case 4: costMenu(); break;
			default: System.out.println("이용해주셔서 감사합니다."); return; } System.out.println(); }
	}
	
	public static void main(String[] args)
	{
		MyLibrary mlb = new MyLibrary();
		mlb.doRun();
	}
}
