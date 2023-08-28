package co.yedam.hello.product.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.hello.product.service.ProductService;
import co.yedam.hello.product.service.ProductVO;
import co.yedam.hello.product.serviceImpl.ProductServiceImpl;

public class ProductManager { //제품관리 메뉴
	private Scanner sc = new Scanner(System.in);
	private ProductService dao = new ProductServiceImpl();
	
	private void mainTitle() {
		System.out.println("=========================");
		System.out.println("=====  제  품  관  리 =====");
		System.out.println("=== 1. 제 품 전 체 조 회 ===");
		System.out.println("=== 2. 제 품 한 건 조 회 ===");
		System.out.println("=== 3. 제 품 등 록 ========");
		System.out.println("=== 4. 제 품 수 정 ========");
		System.out.println("=== 5. 제 품 삭 제 ========");
		System.out.println("=== 6. 종           료 ===");
		System.out.println("=========================");
		System.out.println("= 작업번호를 선택 하세요..");
	}
	public void run() {
		boolean b = false;
		do {
			mainTitle();
			int jobCode = sc.nextInt();
			sc.nextLine();
			switch(jobCode) {
			case 1:
				//제품 조회 루틴
				System.out.println("----------------------------");
				System.out.println("--------제 품 전 체 목 록-------");
				productList();
				System.out.println("----------------------------");
				break;
			case 2:
				//제품 한건 조회 루틴
				System.out.println("----------------------------");
				System.out.println("조회할 제품코드를 입력하세요");
				String id = sc.nextLine();
				productSelect(id);
				System.out.println("----------------------------");
				break;
			case 3:
				//제품 등록 루틴
				System.out.println("---------제 품 등 록----------");
				productInsert();
				break;
			case 4:
				//제품 수정 루틴
				System.out.println("---------제 품 수 정----------");
				productUpdate();
				break;
			case 5:
				//제품 삭제 루틴
				System.out.println("---------제 품 삭 제----------");
				productDelete();
				break;
			case 6:
				//작업종료 루틴
				b=true;
				System.out.println("작업이 종료 됩니다.");
				sc.close();
				break;
			}
		} while(!b);
	}
	private void productDelete() {
		ProductVO vo= new ProductVO();
		System.out.println("수정할 제품의 코드를 입력하세요");
		vo.setProductId(sc.nextLine());
		int n = dao.productDelete(vo);
		if(n!=0) {
			System.out.println("제품 정보 삭제가 성공했습니다.......");
		} else {
			System.out.println("제품 정보 삭제가 실패했습니다.......");
		}
	}
	private void productUpdate() {
		//먼저 수정항목 선택
		ProductVO vo = new ProductVO();
		System.out.println("수정할 제품의 코드를 입력하세요");
		vo.setProductId(sc.nextLine());
		subtitle();
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1:
			System.out.println("=== 제품명을 입력하세요 ===");
			vo.setProductName(sc.nextLine());
			System.out.println("=== 제품가격을 입력하세요 ===");
			vo.setProductPrice(sc.nextInt());
			sc.nextLine();
			System.out.println("=== 제품모델을 입력하세요 ===");
			vo.setProductModel(sc.nextLine());
			break;
		case 2:
			System.out.println("=== 제품명을 입력하세요 ===");
			vo.setProductName(sc.nextLine());
			break;
		case 3:
			System.out.println("=== 제품가격을 입력하세요 ===");
			vo.setProductPrice(sc.nextInt());
			break;
		case 4:
			System.out.println("=== 제품모델을 입력하세요 ===");
			vo.setProductModel(sc.nextLine());
			break;

		}
		int n=dao.productUpdate(vo);
		if(n!=0) {
			System.out.println("제품 정보 변경이 성공했습니다.......");
		} else {
			System.out.println("제품 정보 변경이 실패했습니다.......");
		}
	}
	private void subtitle() {
		System.out.println("=========================");
		System.out.println("===수정할 항목번호를 선택하세요");
		System.out.println("=========================");
		System.out.println("== 1. 모든 항목 ===========");
		System.out.println("== 2. 제 품 명 ===========");
		System.out.println("== 3. 제 품 가 격 =========");
		System.out.println("== 4. 제 품 모 델 =========");
		System.out.println("=========================");
		System.out.println("수정항목 선택??==> "); 
	}
	private void productInsert() {
		ProductVO vo = new ProductVO();
		System.out.println("==제품 코드를 입력하세요");
		vo.setProductId(sc.nextLine());
		System.out.println("==제품명을 입력하세요");
		vo.setProductName(sc.nextLine());
		System.out.println("==제품가격을 입력하세요");
		vo.setProductPrice(sc.nextInt());
		sc.nextLine();
		System.out.println("==제품모델을 입력하세요"); 
		vo.setProductModel(sc.nextLine());
		
		int n = dao.productInsert(vo);
		
		if(n!=0) {
			System.out.println("==제품 등록이 성공 했다.");
		} else {
			System.out.println("==제품 등록이 실패 했다.");
		}
	}
	private void productSelect(String id) {
		ProductVO vo = new ProductVO();
		vo.setProductId(id);
		vo = dao.productSelect(vo);
		if(vo != null) {
			vo.toString();
		} else {
			System.out.println("제품코드가 존재하지 않습니다."); 
		}
	}
	private void productList() {
		// 제품 전체 목록 조회
		
		List<ProductVO> products = new ArrayList<ProductVO>();
		
		products = dao.productSelectList();
		for (ProductVO v : products) {
			v.toString();
		}
		
	}
}
