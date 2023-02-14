package Vidpitch_Scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;

public class Video_Scheduler {
	
	private static final String String = null;
	Connection con;
	static PreparedStatement pst;
	ResultSet rs;
	static String status;
	static String id;
	static String start_activated_on;
	static String end_activated_on;
	static String total_records_qualified_and_processed;
	

	
	@Test()
    public void Check_Video_Schedular() throws ClassNotFoundException, SQLException {
		 
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Calendar obj = Calendar.getInstance();
    String str = formatter.format(obj.getTime());
    System.out.println("Current Date: "+str );
   
		Xls_Reader reader = new Xls_Reader("C:\\Users\\47Billion\\Desktop\\xlsx\\Scheduler.xlsx");
		
		ArrayList<java.lang.String> id1 = new ArrayList<String>();
		 ArrayList<java.lang.String> status1 = new ArrayList<String>();
		 ArrayList<java.lang.String> start_activated_on1 = new ArrayList<String>();
		 ArrayList<java.lang.String> end_activated_on1 = new ArrayList<String>();
		 ArrayList<java.lang.String> total_records_qualified_and_processed1 = new ArrayList<String>();
		 
		
		
		    Connection con;
		    Class.forName("org.postgresql.Driver");
		    con = DriverManager.getConnection("jdbc:postgresql://34.236.14.77:5434/videoms", "videomsusr", "videoms1236");   
		    pst=con.prepareStatement("select id,status,started_at,ended_at,details -> 'startActivatedOn' as start_activated_on, details -> 'endActivatedOn' as end_activated_on, details -> 'searchAssetCount' as search_asset_count, details -> 'totalAssetsProcessed' as total_assets_processed, details -> 'searchAssetMinPriceCount' as search_asset_min_price_count, details -> 'assetWithMinReqImagesCount' as asset_with_min_req_images_count, details -> 'totalProcessingTime' as total_processing_time, jsonb_array_length(details -> 'successAssetIds') as total_records_qualified_and_processed, details -> 'successAssetIds' from scheduler_history where name='sync-assets-video-processing' order by id desc limit 4;");
		   
		    ResultSet rs = pst.executeQuery();
		    
		    while(rs.next()) {
		    	id=rs.getString("id");
		    	id1.add(id);
		    	
		    	status =rs.getString("status");
		    	status1.add(status);
		    	
		    	start_activated_on =rs.getString("start_activated_on");
		    	start_activated_on1.add(start_activated_on);
		    	
		    	end_activated_on =rs.getString("end_activated_on");
		    	end_activated_on1.add(end_activated_on);
		    	
		    	total_records_qualified_and_processed = rs.getString("total_records_qualified_and_processed");
		    	total_records_qualified_and_processed1.add(total_records_qualified_and_processed);
		    	
		    	
		    	
		  	 		
		 		
		    //	project =reader.getCellData("data", "uuid", 2);
		     
		   
		    	 
		    	//reader.setCellData("data","uuid",i,uuid);
		    }	
		    
		    
		    
		    
		    
		    for(int i=0;i<status1.size();i++){
		    	
		    	reader.setCellData("data","id",i+2,id1.get(i));
		    	reader.setCellData("data","status",i+2,status1.get(i));
		    	reader.setCellData("data","start_activated_on",i+2,start_activated_on1.get(i));
		    	reader.setCellData("data","end_activated_on",i+2,end_activated_on1.get(i));
		    	reader.setCellData("data","total_records_qualified_and_processed",i+2,total_records_qualified_and_processed1.get(i));
		    	
		    	 }
		    
		   String start = start_activated_on1.get(0);
		   String compare = start.substring(1,11);
		   System.out.println(compare);
		   String Status1 = status1.get(0);
		   String Status2 = status1.get(1);
		   String Status3 = status1.get(2);
		   String Status4 = status1.get(3);
			  
		   
		   if (compare.equals(str)) {
			   
			  Assert.assertEquals(Status1, "complete");
			  Assert.assertEquals(Status2, "complete");
			  Assert.assertEquals(Status3, "complete");
			  Assert.assertEquals(Status4, "complete");
			   
			   
		   }
		   
		   
		   else {
			   
			   Assert.assertEquals(Status1, "No current date record found");
		   }	
			  
}	
	
	

}
