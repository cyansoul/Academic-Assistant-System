/* 计算日期  */
//获得某一月的开始日期和结束日期
function getMonthArea(year,month){
	var flag = false;
	//计算是不是闰年
	 if((year%4==0 && year%100!=0)||(year%100==0 && year%400==0)){
		 flag = true;
	 }
	 var arr = {
		 startDate:year + "-" + month + "-01",
		 endDate:""
	 };
	 if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		 arr.endDate = year + "-" + month + "-31";
	 }else if(month==2){
		 if(flag){
			 arr.endDate = year + "-" + month + "-29";
		 }else{
			 arr.endDate = year + "-" + month + "-28";
		 }
	 }else{
		 arr.endDate = year + "-" + month + "-30";
	 }
	 return arr;
}