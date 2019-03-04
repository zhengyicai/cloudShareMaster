package com.qzi.cms.common.util;


import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/11/26.
 */
public class SoundwavUtils {
    private static int CODE_WIDTH = 1764;
    private static int WIDTH_FREQ = 1764;
    private static int WIDTH_SPACE = (CODE_WIDTH-WIDTH_FREQ);

    private static int WIDTH_1_3 = (CODE_WIDTH/3);
    private static int WIDTH_2_3 = (CODE_WIDTH*2/3);
    private static int WIDTH_3_3 = CODE_WIDTH;
    private static int WIDTH_4_3 = (CODE_WIDTH*4/3);

    private static int SYNC_WIDTH = 3528; //同步头码元宽度，882--20ms
    private static int SYNC_WIDTH_FREQ = 2646;
    private static int SYNC_WIDTH_SPACE = (SYNC_WIDTH-SYNC_WIDTH_FREQ);
    private static int SYNC_WIDTH_1_3 = (SYNC_WIDTH/3);
    private static int SYNC_WIDTH_2_3 = (SYNC_WIDTH*2/3);
    private static int SYNC_WIDTH_3_3 = SYNC_WIDTH;
    private static int SYNC_WIDTH_1_2 = (SYNC_WIDTH/2);
    private static int SYNC_WIDTH_4_3 = (SYNC_WIDTH*4/3);

    private static int ENV_STEP = 530;

    private static void writeWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                            long totalDataLen, long longSampleRate, int channels, long byteRate)throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';  //WAVE
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16;  // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1;   // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (1 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16;  // bits per sample
        header[35] = 0;
        header[36] = 'd'; //data
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }

    /*************************************************
     名称：envelope
     作用：对声波码元有频率部分加斜坡进行调制
     参数：
     int j--采集点
     int frontStep--前肩斜坡的点数
     int backStep--前肩斜坡的点数
     int begin--起始点
     int end--结束点
     返回：
     调制值
     ******************************************************/
    static float envelope(int j,int frontStep,int backStep,int begin,int end)
    {
        if((j-begin)<frontStep) return ((float)(j-begin)/(float)frontStep);
        if((end-j)<backStep) return ((float)(end-j)/(float)backStep);
        return 1;
    }

    /*************************************************
     名称：getFreqData
     作用：得到特定频率的采集点数据, 同时对声波码元有频率部分加斜坡进行调制
     参数：
     int j--采集点
     float freq--特定频率，以HZ为单位
     int begin--起始点
     int end--结束点
     返回：
     特定频率的采集点数据
     ******************************************************/
    static short getFreqData(int j,float freq,int begin,int end,int frontStep,int backStep)
    {
        return (short) Math.round(32767*Math.sin(2*Math.PI*freq*j/44100)*envelope(j,frontStep,backStep,begin,end));
    }

    public static void WriteCommmand(FileOutputStream fout,String sCommand) throws IOException{
        int sLen=sCommand.length();
        int j;
        short m_pcmData;
        byte[] dataFreq = new byte[2];

        for(int i=0;i<sLen;i++)
        {
            String d = sCommand.substring(i, i+1);
            if(d.equals("0")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData = getFreqData(j,14100,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("1")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData = getFreqData(j,14150,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("2")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14200,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("3")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14250,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("4")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14300,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("5")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14350,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("6")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData = getFreqData(j,14400,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("7")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14450,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("8")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14500,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equals("9")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14550,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(m_pcmData);
                }
            }else if(d.equalsIgnoreCase("a")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14600,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equalsIgnoreCase("b")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14650,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equalsIgnoreCase("c")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14750,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equalsIgnoreCase("d")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData = getFreqData(j,14800,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
            }else if(d.equalsIgnoreCase("e")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData =getFreqData(j,14850,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equalsIgnoreCase("f")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData = getFreqData(j,14900,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }else if(d.equalsIgnoreCase("r")){
                for(j=0;j<WIDTH_FREQ;j++)
                {
                    m_pcmData = getFreqData(j,14700,0,WIDTH_FREQ,49,ENV_STEP);
                    dataFreq[1] = (byte) ((m_pcmData >> 8) & 0xff);
                    dataFreq[0] = (byte) ((m_pcmData) & 0xff);
                    fout.write(dataFreq);
                }
                for(j=0;j<WIDTH_SPACE;j++)
                {
                    m_pcmData = 0;
                    dataFreq[1] = 0;
                    dataFreq[0] = 0;
                    fout.write(dataFreq);
                }
            }

        }
    }

    //---------------采用计算法产生音频文件----------------------------
    public static boolean GenerateWaveFile2(String sFile,String sCommand)
    {
        int sDataLen = (int)(2*SYNC_WIDTH+sCommand.length()*2*CODE_WIDTH)+44100;



        try{
            FileOutputStream fout = new FileOutputStream(sFile);
            writeWaveFileHeader(fout,sDataLen,sDataLen+36,44100,1,16*44100/8);

            byte[] dataNull = new byte[44100];
            fout.write(dataNull);

            byte[] dataFreq = new byte[2];
            int j;
            //写入帧起始
            for(j=0;j<SYNC_WIDTH_FREQ;j++)
            {
                short data = getFreqData(j,14700,0,SYNC_WIDTH_FREQ,49,441);
                dataFreq[1] = (byte) ((data >> 8) & 0xff);
                dataFreq[0] = (byte) ((data) & 0xff);
                fout.write(dataFreq);
            }

            for(j=0;j<SYNC_WIDTH_SPACE;j++)
            {
                short data = getFreqData(j,14950,0,SYNC_WIDTH_SPACE,49,688);
                dataFreq[1] = (byte) ((data >> 8) & 0xff);
                dataFreq[0] = (byte) ((data) & 0xff);
                fout.write(dataFreq);
            }

            WriteCommmand(fout,sCommand);
            fout.flush();
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public static String Format(String value)
    {
        String s = "";
        String olddata = "";
        for(int i=0;i<value.length();i++)
        {
            String strIndex = value.substring(i, i+1);
            if(olddata.length() == 0 || !olddata.equals(strIndex)){
                s += strIndex;
                olddata = strIndex;
            }else{
                s += "R";
                olddata = "R";
            }
        }

        return s;
    }

    public static String  PlaySound(byte[] data) throws IOException {
        String strwav = "";
        byte crc = CRC8Utils.crc8(data,data.length);

        byte[] dataEncode = CRC8Utils.encode(data, crc);
        byte crc2 = CRC8Utils.crc8_Reverse(dataEncode,dataEncode.length);


        System.out.print("PlaySound :");
        for (int i = 0; i < data.length; i++) {
            System.out.print(Integer.toHexString(data[i]&0xff)+" ");
        }
        System.out.println();

        for (int i = 0; i < dataEncode.length; i++) {
            String str2 = String.format("%02x", dataEncode[i]);
            strwav += str2;
        }

        strwav += String.format("%02x", crc2);


        strwav =  Format(strwav);


        String str = ToolUtils.getUUID();


        //存放目录
        //GenerateWaveFile2("d:/test/"+str+".wav",strwav);
        GenerateWaveFile2("/data/page/sound/"+str+".wav",strwav);

        //设置liunx 读写文件权限
        Runtime.getRuntime().exec("chmod 777 -R " + "/data/page/sound/"+str+".wav");

        return  str+".wav";








    }



}
