package com.example.zhoubiao.cxcourses.constant;

public class Constant {
	public static final String POSITION_NUMBER="course_number";
	public static final String LIST_TYPE="list_type";
	public static final String IS_SEARCHABLE="is_searchable";
	public static final String LESSON_JSON_FILE_PREFIX="Course";
	public static final String JSON_FILE_SUFFIX=".txt";
	public static final String LESSON_NUMBER="lesson_number";
	public static final String JSON_COURSE_NAME="courseName";
	public static final String JSON_COURSE_INTRODUCTION="courseIntroduction";
	public static final String JSON_COURSE_ICON="courseIcon";
	public static final int LIST_REFRESH = 0;
	public static final String 	COURSE_JSON_NAME="CourseJSON";
	public static final String TOOLS_JSON_NAME="Tools";
	public static final int TYPE_COURSE=0;
	public static final int TYPE_TOOLS=1;
	public static final String Contradiction_Matrix="矛盾矩阵";
	public static final String CASES_ARRAY="cases_array";
	public static final String LOGIN_USER_INFO_FILE="login_user_info_file";
	public static final String SP_IS_LOGIN="is_login";
	public static final String SP_USER_NAME="user_name";
	public static final String SP_USER_ID="user_id";
	public static final String SP_USER_AVATAR_URL="user_avatar_url";
	public static final int RESULT_CODE_LOGIN=20; 
	public static final String ACTION_BAR_TITLE="action_bar_title";
	public static final String MAIN_SELECTION_SELECTED_POSITION="main_selection";
/*	private LinkedList<CommonCourse> creativeCourse=new LinkedList<CommonCourse>();
	private LinkedList<CommonCourse> lessons=new LinkedList<CommonCourse>();*/
	/*public  LinkedList<CommonCourse> getCreativeCourse()
	{
		if(creativeCourse.size()==0)
		{
			JSONArray array=new JSONArray();

				for(int i=0;i<courseContent.length;i++)
				{
					CommonCourse course=new CommonCourse();
					course.setCourseName(courseContent[i][0]);
					course.setCourseIntroduction(courseContent[i][1]);
					creativeCourse.add(course);
					JSONObject object=new JSONObject();
					try {
						object.put("courseName", courseContent[i][0]);
						object.put("courseIntroduction", courseContent[i][1]);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					array.put(object);
				}
			for(int i=0;i<30;i++)
			{
				CommonCourse course=new CommonCourse();
				course.setCourseName(i+"sajfldsjflskjflksdjflsdflksj");
				course.setCourseIntroduction(i+"通过对海量的高级别发明专利进行分析、研究和总结，Altshuller发现了一个现象：发明家们用来求解发明问题的方法是有限的，就那么几十种。于是，他将这些方法中比较常用的总结为四十种，这四十种常用的解决发明问题的方法就被称为40个发明原理（Inventive Principle，IP）。同时，Altshuller对这40个发明原理进行了编号，号码是从1到40。每个发明原理都被赋予了一个固定的编号。");
				creativeCourse.add(course);
			}
				Log.v("JSON", array.toString());
		}
		return creativeCourse;
	}*/
	/*public  LinkedList<CommonCourse> getLessons(int courseNumber)
	{
		if(courseNumber!=0)
			courseNumber=0;
			for(int i=0;i<lessonContent[courseNumber].length;i++)
			{
				CommonCourse lesson=new CommonCourse();
				lesson.setCourseName(lessonContent[courseNumber][i][0]);
				lesson.setCourseIntroduction(lessonContent[courseNumber][i][1]);
				lessons.add(lesson);
			}
			return lessons;
	}*/
	/*private String[][] courseContent={
			{"TRIZ概述","TRIZ是由前苏联工程师、发明家根里奇•阿奇舒勒（Genrich S. Altshuller）于1946年开始，在研究了来自于世界各国的大量高水平的发明专利的基础上，提出的一套体系相对完整的发明问题求解理论。"},
			{"技术系统进化","亚里斯多德早就说过“整体大于部分之和”。因此，对系统的研究可以说从古代就已经开始了。"},
			{"矛盾矩阵和发明原理","通过对海量的高级别发明专利进行分析、研究和总结，Altshuller发现了一个现象：发明家们用来求解发明问题的方法是有限的，就那么几十种。于是，他将这些方法中比较常用的总结为四十种，这四十种常用的解决发明问题的方法就被称为40个发明原理（Inventive Principle，IP）。同时，Altshuller对这40个发明原理进行了编号，号码是从1到40。每个发明原理都被赋予了一个固定的编号。"},
			{"物场模型和标准解","模型是对系统原型的抽象，是科学认识的基础和决定性环节。在科学研究中，经常需要进行科学抽象。通过科学抽象，就可以利用模型来揭示研究对象的规律性。"},
			{"科学效应库","纵观人类文明的发展史，科学技术的每一次重大突破，都会对人类社会的发展产生巨大的影响。人类现有的工程技术产品和方法都是在漫长的文明发展过程中，以一定的科学原理为基础，一点一滴地积累起来的。可以毫不夸张地说，人类社会的发展历史就是一部人类发现并利用蕴含在自然界中的科学原理和知识的历史。"}
			
	
	};*/
	
	/*private String[][][] lessonContent={
			{
				{"TRIZ之父——Genrich Altshulle简介","我要向您介绍的是一位超凡脱俗的人，他的超凡脱俗不仅在于他提出了一门奇妙的创造科学，更在于他从不索取回报，他从未说过“给我”，他总是说：“请将这个拿去”。他就是根里奇•阿奇舒勒（Genrich Altshuller）。"},
				{"TRIZ简介","经典TRIZ的理论体系结构\nTRIZ发展史"},
				{"发明的五个级别","在人类进化发展的历史长河中，无数的先贤们利用自己的创造力推动了人类社会的发展。今天，当我们回顾历史的时候，我们往往只注意到那些给人类社会发展带来巨大影响的发明创造，例如：制陶技术为人类提供了最早的人造容器；冶炼技术为人类提供了最早的金属制品 —— 青铜器；十进位计数法为科学的发展奠定了基础；造纸术对人类文化的传播产生了广泛、久远影响；指南针对航海产生了深远的影响；火药改变了整个世界事物的面貌和状态等等。很少有人会注意到那些对已有事物进行的修修补补式的小发明、小创造。而正是由于有了这些小的修修补补式的小发明、小创造，才有了我们现在所看到的各种各样功能相对完善、结构相对简单的生产工具和生活用品。所以，伟大的发明给社会的发展提供了巨大的推动力，但是那些看似小得多的发明创造却是伟大发明的基础，只有在无数小发明、小创造的推动下，伟大的发明才得以出现，并逐步趋于完善。"}
				
			},
			
			
	};*/
	public static String[][] matrix={{"35,28,31,08,02,03,10","03,19,35,40,01,26,02","17,15,08,35,34,28,29,30,40","15,17,28,12,35,29,30","28,17,29,35,01,31,04","17,28,01,29,35,15,31,04",
    	 "28,29,07,40,35,31,02","40,35,02,04,07","03,35,14,17,04,07","31,28,26,07,02,03,05,40","02,05,07,04,34,10","10,05,34,16,02","15,02,25,19,38,18","10,30,35,28,08,37,18","35,10,19,03,34,31,12"
    	 ,"35,15,01,28,07,14,34,39","19,30,36,31,12,18,14,25,01","10,40,30,36,37,31,04,03,12","28,31,40,35,10,30,18,14,04","35,01,30,39,29,21,07,19,12","02,40,06,31,36,29,04,38,32","01,35,32,38,13,19,23"
    	 ,"01,02,28,03,35,25","05,35,31,24,03,04,07,40","10,19,28,20,35,01,02,03,16","19,06,03,10,02,13,34,12","10,24,11,35,25","35,02,13,03,05,39,14","02,30,39,25,10,21,31,35,27","02,30,39,25,10,21,31,35,27"
    	 ,"02,35,10,39,03,31,22,24,27","29,28,15,02,12,05,07,34","10,15,13,05,24,25,26","02,15,03,11,25,23,35,24,01","03,17,01,35,27,14,11,04,31","02,28,17,27,03,30,33,11,14","02,05,06,23,24,28","26,10,17,13,35,02,05,28",
    	 "03,01,31,14,40,30","05,24,27,21,02,25,18,31,35","01,05,36,25,16,14,27,24,03","28,05,26,35,25,01,18,24,,03","28,05,26,35,25,01,18,24,03","35,24,10,28,12,01,37,03,16","26,35,40,30,36,02,10,25,28","10,12,02,19,15,05",
    	 "26,28,32,05,20,36,32,37","28,26,35,10,02,37"}};

}
