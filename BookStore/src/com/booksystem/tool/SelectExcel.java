package com.booksystem.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.booksystem.mybatis.entities.Book;
import com.booksystem.service.BookService;

@Repository
public class SelectExcel {
	
	@Autowired
	private BookService bookService;
	
	private HSSFWorkbook workBook;
	//页数
	private HSSFSheet sheet =null;
	//行数
	private HSSFRow row=null;
	//单元格
	private HSSFCell cell=null;
	//单元格样式
	HSSFCellStyle style =null;
	
	//输出流
	private FileOutputStream os;
	
	
	//生成Excel文档
	public void createExcel(String path) throws IOException{
		
		os=new FileOutputStream(path);
		
		workBook = new HSSFWorkbook();
		//生成页
		sheet=workBook.createSheet("第一页");
		
		//设置列宽度
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(4, 6000);
		
		
		//字体大小
		HSSFFont font = workBook.createFont();
		font.setFontHeight((short)500);
		//设置单元格样式
		style = workBook.createCellStyle();
		//水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//垂直居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//设置字体大小
		style.setFont(font);
		
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0,3,0,10));
		//生成第一行
		row=sheet.createRow(0);
		//生成单元格
		cell=row.createCell(0,HSSFCell.CELL_TYPE_STRING);
		//添加内容
		cell.setCellValue("所有图书");
		//添加样式
		cell.setCellStyle(style);
		
		
		
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		//水平居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//垂直居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//添加第二行
		row=sheet.createRow(4);
		cell=row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("序号");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(1, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("编号");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("书名");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(3, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("作者");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(4, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("出版社");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(5, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("ISBN");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(6, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("价格");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(7, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("库存");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(8, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("可借数");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(9, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("类型");
		cell.setCellStyle(cellStyle);
		
		cell=row.createCell(10, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("借阅次数");
		cell.setCellStyle(cellStyle);
		
		List<Book> list = bookService.selectAllBook();
		
		int tmp=1;
		for (Book book : list) {
			row=sheet.createRow(4+tmp);
			cell=row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tmp++);
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(1, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookId());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookName());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(3, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookAuthor());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookPublih());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(5, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookIsbn());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(6, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookPrice());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(7, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookStore());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(8, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookLend());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(9, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookStyle());
			cell.setCellStyle(cellStyle);
			
			cell=row.createCell(10, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(book.getBookLendtimes());
			cell.setCellStyle(cellStyle);
			
		}
		
		workBook.write(os);
		os.close();
	}
	
	
	//删除文件
	public void deletExcel(){
		
		
		
	}
	
	public SelectExcel(){
		
		System.out.println("SelectExcel");
		
		
	}
	
	
	
	
	

}
