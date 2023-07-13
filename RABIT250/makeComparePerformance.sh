#!/bin/zsh

#Configuration:
La=6
Processor_Num=8
BLAFair_getavoid_devideNum=10

# Save arguments to variables
if [ "$1" != "" ] && [ "$2" != "" ]; then
    First_Automata=$1
    Second_Automata=$2
else
    First_Automata="./Examples/bakery.1.c.ba"
    Second_Automata="./Examples/fischer.3.c.ba"
fi

result_file="./results/output.txt"
echo "" > $result_file

javac ./mainfiles/ComparePerformance.java
if [ $? -ne 0 ]; then
    Compile_Status="Error occurred during compilation."
else
    Compile_Status="Compilation successful."
fi

jar cvfe ComparePerformance.jar mainfiles.ComparePerformance mainfiles/ComparePerformance.class automata/*.class datastructure/*.class comparator/*.class algorithms/*.class
if [ $? -ne 0 ]; then
    Jar_Status="Error occurred during jar."
else
    Jar_Status="jar successful."
fi

# Check if both variables are equal to specific strings
if [[ $Compile_Status == "Compilation successful." ]] && [[ $Jar_Status == "jar successful." ]]; then
    clear
    echo $Compile_Status
    echo $Jar_Status

    echo "The compared two automata files are: " >> $result_file
    echo "1.  "$First_Automata >> $result_file
    echo "2.  "$Second_Automata >> $result_file

    echo "" >> $result_file

    echo "The created processsor number is:  "$Processor_Num >> $result_file

    echo "The compared two automata files are: "
    echo "1.  "$First_Automata
    echo "2.  "$Second_Automata

    echo ""

    echo "The created processor number is:  "$Processor_Num

    for a in {1..12}
    do
      La=$a
      for j in {1..20}
      do
        BLAFair_getavoid_devideNum=$j

        echo "" >> $result_file
        echo ""

        echo "*************  La: "$La", BLAFair_getavoid_devideNum: "$BLAFair_getavoid_devideNum"  *********************" >> $result_file
        echo "*************  La: "$La", BLAFair_getavoid_devideNum: "$BLAFair_getavoid_devideNum"  *********************"

        for i in {1..3}
        do
          output=$(java -jar ComparePerformance.jar $First_Automata $Second_Automata $La $Processor_Num $BLAFair_getavoid_devideNum)

          echo "" >> $result_file
          echo "The Running Time "$i": " >> $result_file
          echo $output >> $result_file

          echo ""
          echo "The Running Time "$i": "
          echo $output
        done
      done
    done


#    for i in {1..5}
#    do
#      output=$(java -jar ComparePerformance.jar $First_Automata $Second_Automata $La $Processor_Num $BLAFair_getavoid_devideNum)
#
#      echo "" >> $result_file
#      echo "The Running Time "$i": " >> $result_file
#      echo $output >> $result_file
#
#      echo ""
#      echo "The Running Time "$i": "
#      echo $output
#    done

else
    echo $Compile_Status
    echo $Jar_Status
fi

