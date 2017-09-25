package com.example.zhoubiao.cxcourses.util;

public class ArrayChange {

	public String[] changeArray(String[] stringArray){
		String[] changedArray =new String[stringArray.length];
		for(int i=0;i<stringArray.length;i++){
			switch(Integer.parseInt(stringArray[i])){
				case 1:
					changedArray[i]=stringArray[i]+":分割原理";
					break;
				case 2:
					changedArray[i]=stringArray[i]+":抽取原理";
					break;
				case 3:
					changedArray[i]=stringArray[i]+":局部质量原理";
					break;
				case 4:
					changedArray[i]=stringArray[i]+":增加不对称性原理";
					break;
				case 5:
					changedArray[i]=stringArray[i]+":组合（/合并）原理";
					break;
				case 6:
					changedArray[i]=stringArray[i]+":多功能性（/多用性、广泛性）原理";
					break;
				case 7:
					changedArray[i]=stringArray[i]+":嵌套原理";
					break;
				case 8:
					changedArray[i]=stringArray[i]+":重量补偿原理";
					break;
				case 9:
					changedArray[i]=stringArray[i]+":预先反作用原理";
					break;
				case 10:
					changedArray[i]=stringArray[i]+":预先作用原理";
					break;
				case 11:
					changedArray[i]=stringArray[i]+":预补偿原理（/事先防范原理）";
					break;
				case 12:
					changedArray[i]=stringArray[i]+":等势原理";
					break;
				case 13:
					changedArray[i]=stringArray[i]+":反向作用原理";
					break;
				case 14:
					changedArray[i]=stringArray[i]+":曲率增加（/曲面化）原理";
					break;
				case 15:
					changedArray[i]=stringArray[i]+":动态特性原理";
					break;
				case 16:
					changedArray[i]=stringArray[i]+":未达到或过度的作用原理";
					break;
				case 17:
					changedArray[i]=stringArray[i]+":空间维数变化原理（/一维变多维）";
					break;
				case 18:
					changedArray[i]=stringArray[i]+":机械振动原理";
					break;
				case 19:
					changedArray[i]=stringArray[i]+":周期性作用原理";
					break;
				case 20:
					changedArray[i]=stringArray[i]+":有益（/效）作用的连续性原理";
					break;
				case 21:
					changedArray[i]=stringArray[i]+":减少有害作用的时间";
					break;
				case 22:
					changedArray[i]=stringArray[i]+":变害为利原理";
					break;
				case 23:
					changedArray[i]=stringArray[i]+":反馈原理";
					break;
				case 24:
					changedArray[i]=stringArray[i]+":借助中介物原理";
					break;
				case 25:
					changedArray[i]=stringArray[i]+":自服务原理";
					break;
				case 26:
					changedArray[i]=stringArray[i]+":复制原理";
					break;
				case 27:
					changedArray[i]=stringArray[i]+":廉价替代品原理";
					break;
				case 28:
					changedArray[i]=stringArray[i]+":机械系统替代原理";
					break;
				case 29:
					changedArray[i]=stringArray[i]+":气动与液压结构原理";
					break;
				case 30:
					changedArray[i]=stringArray[i]+":柔性壳体或薄膜原理";
					break;
				case 31:
					changedArray[i]=stringArray[i]+":多孔材料原理";
					break;
				case 32:
					changedArray[i]=stringArray[i]+":颜色改变原理（/改变颜色、拟态）";
					break;
				case 33:
					changedArray[i]=stringArray[i]+":同质性原理（/均质性原理）";
					break;
				case 34:
					changedArray[i]=stringArray[i]+":抛弃和再生原理";
					break;
				case 35:
					changedArray[i]=stringArray[i]+":物理或化学参数改变原理";
					break;
				case 36:
					changedArray[i]=stringArray[i]+":相变原理";
					break;
				case 37:
					changedArray[i]=stringArray[i]+":热膨胀原理";
					break;
				case 38:
					changedArray[i]=stringArray[i]+":强氧化剂原理（/使用强力氧化剂、加速氧化原理）";
					break;
				case 39:
					changedArray[i]=stringArray[i]+":惰性环境原理";
					break;
				default:
					changedArray[i]=stringArray[i]+":复合材料原理";
					break;
			}
		}
		return changedArray;
	}
}
