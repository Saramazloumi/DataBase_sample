package com.midterm.lasalle.collegedatabasebyfile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Employee;
import model.FileManager;
import model.Person;
import model.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener {
    EditText editTextCaption, editTextID, editTextAge, editTextJob, editTextSalary, editTextProgram;
    ListView listView;
    Button btnStudent, btnEmployee, btnAll;
    ArrayList<Person> listOfPersons;
    ArrayList<Person> listOfStudent;
    ArrayList<Person> listOfEmployee;
    ArrayAdapter<Person> adapter;
    String flag;
    int currentPosition;

    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        editTextCaption = findViewById(R.id.editTextCaption);
        editTextID = findViewById(R.id.editTextID);
        editTextAge = findViewById(R.id.editTextAge);
        editTextJob = findViewById(R.id.editTextJob);
        editTextSalary = findViewById(R.id.editTextSalary);
        editTextProgram = findViewById(R.id.editTextProgram);
        listView = findViewById(R.id.listView);
        btnStudent = findViewById(R.id.btnStudent);
        btnEmployee = findViewById(R.id.btnEmployee);
        btnAll = findViewById(R.id.btnAll);
        btnStudent.setOnClickListener(this);
        btnEmployee.setOnClickListener(this);
        btnAll.setOnClickListener(this);
        listOfPersons = new ArrayList<Person>();
        listOfStudent = new ArrayList<Person>();
        listOfEmployee = new ArrayList<Person>();
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listOfPersons = FileManager.readInFile(this,"persons.txt");

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Do you like to delete? (Y/N)");
        alertDialog.setPositiveButton("Yes", this);
        alertDialog.setNegativeButton("No", this);
    }


    public ArrayList<Person> filteringStudent (ArrayList<Person> listOfPerson){
        listOfStudent = new ArrayList<>();
        for (int i = 0 ; i < listOfPerson.size(); i++){
            if (listOfPerson.get(i) instanceof Student)
                listOfStudent.add(listOfPerson.get(i));
        }
        return listOfStudent;
    }

    public ArrayList<Person> filteringEmployee (ArrayList<Person> listOfPerson){
        listOfEmployee = new ArrayList<>();
        for (int i = 0 ; i < listOfPerson.size(); i++) {
            if (listOfPerson.get(i) instanceof Employee)
                listOfEmployee.add(listOfPerson.get(i));
        }
        return listOfEmployee;
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btnStudent:
                clearText();
                editTextCaption.setText("Students");
                editTextJob.setVisibility(EditText.INVISIBLE);
                editTextSalary.setVisibility(EditText.INVISIBLE);
                editTextProgram.setVisibility(EditText.VISIBLE);
                filteringStudent(listOfPersons);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listOfStudent);
                listView.setAdapter(adapter);
                flag = "s";
                break;

            case R.id.btnEmployee:
                clearText();
                editTextJob.setVisibility(EditText.VISIBLE);
                editTextSalary.setVisibility(EditText.VISIBLE);
                editTextProgram.setVisibility(EditText.INVISIBLE);
                filteringEmployee(listOfPersons);
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfEmployee);
                listView.setAdapter(adapter);
                flag = "e";
                break;

            case R.id.btnAll:
                clearText();
                editTextJob.setVisibility(EditText.VISIBLE);
                editTextSalary.setVisibility(EditText.VISIBLE);
                editTextProgram.setVisibility(EditText.VISIBLE);
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfPersons);
                listView.setAdapter(adapter);
                flag = "a";
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Student s;
        Employee e;
        if (flag.equals("a") && listOfPersons.get(position) instanceof Student){
            s = (Student) listOfPersons.get(position);
            getStudent(s);
        }else if (flag.equals("a") && listOfPersons.get(position) instanceof Employee){
            e = (Employee) listOfPersons.get(position);
            getEmployee(e);
        }else if (flag.equals("s")){
            s = (Student) listOfStudent.get(position);
            getStudent(s);
        }else if (flag.equals("e")){
            e = (Employee) listOfEmployee.get(position);
            getEmployee(e);
        }
    }

    public void getStudent(Student s){
        String sName = s.getName();
        int sAge = s.getAge();
        int sId = s.getStudentId();
        String sProgram = s.getProgram();

        editTextID.setText(String.valueOf(sId));
        editTextAge.setText(String.valueOf(sAge));
        editTextJob.setVisibility(EditText.INVISIBLE);
        editTextSalary.setVisibility(EditText.INVISIBLE);
        editTextProgram.setVisibility(EditText.VISIBLE);
        editTextProgram.setText(sProgram);
    }

    public void getEmployee(Employee e){
        String eName = e.getName();
        int eAge = e.getAge();
        int eId = e.getEmployeeId();
        String eJob = e.getJob();
        double eSalary = e.getSalary();

        editTextID.setText(String.valueOf(eId));
        editTextAge.setText(String.valueOf(eAge));
        editTextJob.setVisibility(EditText.VISIBLE);
        editTextJob.setText(eJob);
        editTextSalary.setVisibility(EditText.VISIBLE);
        editTextSalary.setText(String.valueOf(eSalary));
        editTextProgram.setVisibility(EditText.INVISIBLE);
    }

    public void clearText(){
        editTextID.setText("");
        editTextAge.setText("");
        editTextJob.setText("");
        editTextSalary.setText("");
        editTextProgram.setText("");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        currentPosition = position;
        AlertDialog alertBox = alertDialog.create();
        alertBox.show();
        return false;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){

            case Dialog.BUTTON_POSITIVE:

                switch (flag){

                    case "a":
                        listOfPersons.remove(currentPosition);
                        adapter.notifyDataSetChanged();
                        break;

                    case "s":
                       Person s = (Student)listOfStudent.remove(currentPosition);
                       listOfPersons.remove(s);
                       adapter.notifyDataSetChanged();
                        break;

                    case "e":
                        Person e = (Employee)listOfEmployee.remove(currentPosition);
                        listOfPersons.remove(e);
                        adapter.notifyDataSetChanged();
                        break;
                }
                break;

            case Dialog.BUTTON_NEGATIVE:
                break;
        }

    }
}
