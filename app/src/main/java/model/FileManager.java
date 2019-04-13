package model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileManager {

    public static ArrayList<Person> readInFile(Context context, String fileName){

        ArrayList<Person> list = new ArrayList<Person>();
        AssetManager assetManager = context.getResources().getAssets();
        try{

            InputStreamReader isr = new InputStreamReader(assetManager.open(fileName));
            BufferedReader br = new BufferedReader(isr);
            String oneLine = br.readLine();

            while (oneLine != null){

                StringTokenizer st = new StringTokenizer(oneLine, ",");
                String type = st.nextToken();

                switch (type){

                    case "E":
                        String eName = st.nextToken();
                        int eAge = Integer.valueOf(st.nextToken());
                        int eId = Integer.valueOf(st.nextToken());
                        String job = st.nextToken();
                        double salary = Double.valueOf(st.nextToken());
                        list.add(new Employee(eName,eAge,eId,job,salary));
                        break;

                    case "S":
                        String sName = st.nextToken();
                        int sAge = Integer.valueOf(st.nextToken());
                        int sId = Integer.valueOf(st.nextToken());
                        String program = st.nextToken();
                        list.add(new Student(sName,sAge,sId,program));
                        break;
                }
                oneLine = br.readLine();
            }
            br.close();
            isr.close();
        }catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
      return list;
    }
}
