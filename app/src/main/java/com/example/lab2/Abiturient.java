package com.example.lab2;

import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Abiturient implements Serializable {
    public String SecondName;
    public String FirstName;
    public String Adress;
    public String Phone;
    public int[] marks;
    public static List<Abiturient> list = new ArrayList<Abiturient>();
    public static List<Abiturient> backupList = new ArrayList<Abiturient>();
    public String toString()
    {
        return "Фамилия: " + SecondName + ".\nИмя: " + FirstName + ".\nАдрес: " + Adress + ".\nТелефон: +375" + Phone + ".\nОценки: " + Arrays.toString(marks);
    }
    public static void Delete(Abiturient deleteAbiturient)
    {
        boolean flag = false;
        int deleteAbiturientId = -1;
        for (Abiturient abiturient : list
        ) {
            if (abiturient.SecondName.compareTo(deleteAbiturient.SecondName) == 0 && abiturient.FirstName.compareTo(deleteAbiturient.FirstName) == 0) {
                deleteAbiturientId = list.indexOf(abiturient);
                flag = true;
            }
        }
        if (flag)
        {
            list.remove(deleteAbiturientId);
        }
    }
    public static List<Abiturient> Bad(double badMarkFilter)
    {
        List<Abiturient> badList = new ArrayList<Abiturient>();
        for (Abiturient abiturient: list)
        {
            double averageMark = 0;
            for (int mark: abiturient.marks)
            {
                averageMark += mark;
            }
            averageMark /= abiturient.marks.length;
            if (averageMark < badMarkFilter) badList.add(abiturient);
        }
        return badList;
    }
    public static List<Abiturient> Average(double badMarkFilter)
    {
        List<Abiturient> averageList = new ArrayList<Abiturient>();
        for (Abiturient abiturient: list)
        {
            double averageMark = 0;
            for (int mark: abiturient.marks)
            {
                averageMark += mark;
            }
            averageMark /= abiturient.marks.length;
            if (averageMark > badMarkFilter) averageList.add(abiturient);
        }
        return averageList;
    }
    public static List<Abiturient> Top(int abiturientCount)
    {
        List<Abiturient> topList = new ArrayList<Abiturient>();
        for (int indexFirst = 0; indexFirst < list.size(); indexFirst++)
        {
            double max = 0;
            int maxIndex = indexFirst;
            for (int indexSecond = indexFirst; indexSecond < list.size(); indexSecond++)
            {
                double averageMark = 0;
                for (int mark: list.get(indexSecond).marks)
                {
                    averageMark += mark;
                }
                averageMark /= list.get(indexSecond).marks.length;
                if (averageMark > max)
                {
                    max = averageMark;
                    maxIndex = indexSecond;
                }
            }
            Abiturient extraVar = list.get(indexFirst);
            list.set(indexFirst, list.get(maxIndex));
            list.set(maxIndex, extraVar);
        }
        return list.subList(0, abiturientCount);
    }
}
