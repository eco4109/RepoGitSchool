/*Programa para escribir en "Disco"
Para poder ser usado por la computadora virtual
*/
import java.io.*;
import java.nio.*;
import java.util.*;

public class writeDisc{
	//1 MB de Ram 
	public static byte[] buffer = new byte[1048576]; 

	public static void main(String[] args){
		setBuffer();
		write("DSK1.dsk",13); //Se escribe en el disco desde la posicion 7 
		System.out.println("\nSe escribió en el disco correctamente ... \n");
	}

	public static void setBuffer(){

		buffer[0]=(byte)0x20; //MUE_DATO_REG
		buffer[1]=(byte)0x03; //AL IP le va a meter: 
		buffer[2]=(byte)0x41; //20 o sea salta a 20
		buffer[3]=(byte)0xA0;
		buffer[4]=(byte)0x00;
		buffer[5]=(byte)0x00;

		buffer[6]=(byte)0x00;
		buffer[7]=(byte)0x01;
		buffer[8]=(byte)0x21;
		buffer[9]=(byte)0x00;
		buffer[10]=(byte)0x02;
		buffer[11]=(byte)0x03;
		buffer[12]=(byte)0xE8;
		buffer[13]=(byte)0x01;
		buffer[14]=(byte)0x00;
		buffer[15]=(byte)0x01;
		buffer[16]=(byte)0x00;
		buffer[17]=(byte)0x01;
		buffer[18]=(byte)0x00;
		buffer[19]=(byte)0x01;

		buffer[20]=(byte)0x60;
		buffer[21]=(byte)0x04;
		buffer[22]=(byte)0x00;
		buffer[23]=(byte)0x00;
		buffer[24]=(byte)0x00;
		buffer[25]=(byte)0x00;

		buffer[26]=(byte)0x47;
		buffer[27]=(byte)0x00;

		buffer[28]=(byte)0x60;
		buffer[29]=(byte)0x06;
		buffer[30]=(byte)0x42;
		buffer[31]=(byte)0xC8;
		buffer[32]=(byte)0x00;
		buffer[33]=(byte)0x00;

		buffer[34]=(byte)0x04;
		buffer[35]=(byte)0x00;

		buffer[36]=(byte)0x60;
		buffer[37]=(byte)0x04;
		buffer[38]=(byte)0x40;
		buffer[39]=(byte)0x80;
		buffer[40]=(byte)0x00;
		buffer[41]=(byte)0x00;

		buffer[42]=(byte)0x47;
		buffer[43]=(byte)0x00;

		buffer[44]=(byte)0x60;
		buffer[45]=(byte)0x06;
		buffer[46]=(byte)0x43;
		buffer[47]=(byte)0x48;
		buffer[48]=(byte)0x00;
		buffer[49]=(byte)0x00;

		buffer[50]=(byte)0x04;
		buffer[51]=(byte)0x00;

		buffer[52]=(byte)0x00;
		buffer[53]=(byte)0x32;

		buffer[54]=(byte)0x20; //empieza un ciclo
		buffer[55]=(byte)0x03;
		buffer[56]=(byte)0x42;
		buffer[57]=(byte)0x50;
		buffer[58]=(byte)0x00;
		buffer[59]=(byte)0x00;

		buffer[60]=(byte)0x00;
		buffer[61]=(byte)0x01;
		buffer[62]=(byte)0x00;
		buffer[63]=(byte)0x01;
		buffer[64]=(byte)0x00;
		buffer[65]=(byte)0x01;
		buffer[66]=(byte)0x00;
		buffer[67]=(byte)0x01;
		buffer[68]=(byte)0x00;
		buffer[69]=(byte)0x01;
		buffer[70]=(byte)0x20;
		buffer[71]=(byte)0x03;
		buffer[72]=(byte)0x00;
		buffer[73]=(byte)0x00;
		buffer[74]=(byte)0x00;
		buffer[75]=(byte)0x00;
		buffer[76]=(byte)0x00;
		buffer[77]=(byte)0x00;
		buffer[78]=(byte)0x00;
		buffer[79]=(byte)0x00;
		buffer[80]=(byte)0x00;
		buffer[81]=(byte)0x00;
		buffer[82]=(byte)0x00;
		buffer[83]=(byte)0x00;
		buffer[84]=(byte)0x00;
		buffer[85]=(byte)0x00;
		buffer[86]=(byte)0x00;
		buffer[87]=(byte)0x00;
		buffer[88]=(byte)0x00;
		buffer[89]=(byte)0x00;
		buffer[90]=(byte)0x00;
		buffer[91]=(byte)0x00;
		buffer[92]=(byte)0x00;
		buffer[93]=(byte)0x00;
		buffer[94]=(byte)0x00;
		buffer[95]=(byte)0x00;
		buffer[96]=(byte)0x00;
		buffer[97]=(byte)0x00;
		buffer[98]=(byte)0x00;
		buffer[99]=(byte)0x00;

		buffer[100]=(byte)0x21;
		buffer[101]=(byte)0x00;
		buffer[102]=(byte)0x42;
		buffer[103]=(byte)0x48;
		buffer[104]=(byte)0x00;
		buffer[105]=(byte)0x00;

		buffer[106]=(byte)0x00;
		buffer[107]=(byte)0x00;
		buffer[108]=(byte)0x00;
		buffer[109]=(byte)0x00;
		buffer[110]=(byte)0x00;
		buffer[111]=(byte)0x00;
		buffer[112]=(byte)0x00;
		buffer[113]=(byte)0x00;
		buffer[114]=(byte)0x00;
		buffer[115]=(byte)0x00;
		buffer[116]=(byte)0x00;
		buffer[117]=(byte)0x00;
		buffer[118]=(byte)0x00;
		buffer[119]=(byte)0x00;
		buffer[120]=(byte)0x00;
		buffer[121]=(byte)0x00;
		buffer[122]=(byte)0x00;
		buffer[123]=(byte)0x00;
		buffer[124]=(byte)0x00;
		buffer[125]=(byte)0x00;
		buffer[126]=(byte)0x00;
		buffer[127]=(byte)0x00;
		buffer[128]=(byte)0x00;
		buffer[129]=(byte)0x00;
		buffer[130]=(byte)0x00;
		buffer[131]=(byte)0x00;
		buffer[132]=(byte)0x00;
		buffer[133]=(byte)0x00;
		buffer[134]=(byte)0x00;
		buffer[135]=(byte)0x00;
		buffer[136]=(byte)0x00;
		buffer[137]=(byte)0x00;
		buffer[138]=(byte)0x00;
		buffer[139]=(byte)0x00;
		buffer[140]=(byte)0x00;
		buffer[141]=(byte)0x00;
		buffer[142]=(byte)0x00;
		buffer[143]=(byte)0x00;
		buffer[144]=(byte)0x00;
		buffer[145]=(byte)0x00;
		buffer[146]=(byte)0x00;
		buffer[147]=(byte)0x00;
		buffer[148]=(byte)0x00;
		buffer[149]=(byte)0x00;
		buffer[150]=(byte)0x00;
		buffer[151]=(byte)0x00;
		buffer[152]=(byte)0x00;
		buffer[153]=(byte)0x00;
		buffer[154]=(byte)0x00;
		buffer[155]=(byte)0x00;
		buffer[156]=(byte)0x00;
		buffer[157]=(byte)0x00;
		buffer[158]=(byte)0x00;
		buffer[159]=(byte)0x00;
		buffer[160]=(byte)0x00;
		buffer[161]=(byte)0x00;
		buffer[162]=(byte)0x00;
		buffer[163]=(byte)0x00;
		buffer[164]=(byte)0x00;
		buffer[165]=(byte)0x00;
		buffer[166]=(byte)0x00;
		buffer[167]=(byte)0x00;
		buffer[168]=(byte)0x00;
		buffer[169]=(byte)0x00;
		buffer[170]=(byte)0x00;
		buffer[171]=(byte)0x00;
		buffer[172]=(byte)0x00;
		buffer[173]=(byte)0x00;
		buffer[174]=(byte)0x00;
		buffer[175]=(byte)0x00;
		buffer[176]=(byte)0x00;
		buffer[177]=(byte)0x00;
		buffer[178]=(byte)0x00;
		buffer[179]=(byte)0x00;
		buffer[180]=(byte)0x00;
		buffer[181]=(byte)0x00;
		buffer[182]=(byte)0x00;
		buffer[183]=(byte)0x00;
		buffer[184]=(byte)0x00;
		buffer[185]=(byte)0x00;
		buffer[186]=(byte)0x00;
		buffer[187]=(byte)0x00;
		buffer[188]=(byte)0x00;
		buffer[189]=(byte)0x00;
		buffer[190]=(byte)0x00;
		buffer[191]=(byte)0x00;
		buffer[192]=(byte)0x00;
		buffer[193]=(byte)0x00;
		buffer[194]=(byte)0x00;
		buffer[195]=(byte)0x00;
		buffer[196]=(byte)0x00;
		buffer[197]=(byte)0x00;
		buffer[198]=(byte)0x00;
		buffer[199]=(byte)0x00;
		buffer[200]=(byte)0x21;
		buffer[201]=(byte)0x00;
		buffer[202]=(byte)0x42;
		buffer[203]=(byte)0xC8;
		buffer[204]=(byte)0x00;
		buffer[205]=(byte)0x00;
		buffer[206]=(byte)0x45;
		buffer[207]=(byte)0x00;
		buffer[208]=(byte)0x70;
		buffer[209]=(byte)0x06;
		buffer[210]=(byte)0x00;
		buffer[211]=(byte)0x00;
		buffer[212]=(byte)0x00;
		buffer[213]=(byte)0x8A;
		buffer[214]=(byte)0x01;
		buffer[215]=(byte)0x00;
		buffer[216]=(byte)0x70;
		buffer[217]=(byte)0x04;
		buffer[218]=(byte)0x00;
		buffer[219]=(byte)0x00;
		buffer[220]=(byte)0x00;
		buffer[221]=(byte)0x01;
		buffer[222]=(byte)0x45;
		buffer[223]=(byte)0x00;
		buffer[224]=(byte)0x70;
		buffer[225]=(byte)0x06;
		buffer[226]=(byte)0x00;
		buffer[227]=(byte)0x00;
		buffer[228]=(byte)0x00;
		buffer[229]=(byte)0x63;
		buffer[230]=(byte)0x01;
}

	public static void write(String Archivo, int pos){
		try{
			RandomAccessFile binfile = new RandomAccessFile(Archivo, "rw");
			binfile.seek(pos);
			binfile.write(buffer);
			binfile.close();

		} catch(IOException ex){
			ex.printStackTrace();
			System.out.println("\n\7 Fatal error");
			System.exit(0);
			}
		}
}
