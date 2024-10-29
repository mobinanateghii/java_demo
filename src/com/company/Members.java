package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Members {
    private String[] membersName;
    private String[] membersGender;
    private Integer[] membersAge;
    private Boolean[] memberCapacityIndex;
    Scanner scanner = new Scanner(System.in);

    public void manageMembers() {
        System.out.print("enter number of possible member: ");
        int memberCount = scanner.nextInt();
        if (memberCount < 1) {
            System.out.println("number of possible member is not valid: \n");
            return;
        }

        this.membersName = new String[memberCount];
        this.membersGender = new String[memberCount];
        this.membersAge = new Integer[memberCount];
        this.memberCapacityIndex = new Boolean[memberCount];
        Arrays.fill(this.memberCapacityIndex, true);

        this.showMenu();
    }

    private void showMenu() {
        boolean resume = true;
        do {
            System.out.println("1) Add member ");
            System.out.println("2) Delete member ");
            System.out.println("3) Edit member ");
            System.out.println("4) Show member ");
            System.out.println("5) Exit ");
            System.out.print("Please enter a number between 1 and 5: ");

            int choiceNumber = scanner.nextInt();
            if (choiceNumber > 5 || choiceNumber < 1) {
                System.out.println("choice Number is not valid!");
                return;
            }

            if (choiceNumber == 1) {
                this.addMember();
            } else if (choiceNumber == 2) {
                this.deleteMember();
            } else if (choiceNumber == 3) {
                this.editMember();
            } else if (choiceNumber == 4) {
                this.showMember();
            } else {
                resume = false;
            }

        } while (resume);
    }


    private void addMember() {
        Integer memberIndex = this.getFreeMemberIndex();
        if (memberIndex == -1)
            return;

        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("Gender: ");
        String gender = scanner.next();

        System.out.print("Age: ");
        int age = scanner.nextInt();

        this.memberCapacityIndex[memberIndex] = false;
        this.membersName[memberIndex] = name;
        this.membersAge[memberIndex] = age;
        this.membersGender[memberIndex] = gender;

        System.out.println("New Member Id :" + memberIndex + 1 + "\n");
    }

    private void showMember() {
        Integer memberIndex = this.getMemberIndex();
        if (memberIndex == -1)
            return;

        System.out.println("Name:  " + this.membersName[memberIndex]);
        System.out.println("Gender:  " + this.membersAge[memberIndex]);
        System.out.println("Age:  " + this.membersGender[memberIndex]);
        System.out.println("\n");
    }

    private void editMember() {
        Integer memberIndex = this.getMemberIndex();
        if (memberIndex == -1)
            return;

        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("Gender: ");
        String gender = scanner.next();

        System.out.print("Age: ");
        int age = scanner.nextInt();

        this.membersName[memberIndex] = name;
        this.membersAge[memberIndex] = age;
        this.membersGender[memberIndex] = gender;
    }

    private void deleteMember() {
        Integer memberIndex = this.getMemberIndex();
        if (memberIndex == -1)
            return;

        this.memberCapacityIndex[memberIndex] = true;
        this.membersName[memberIndex] = null;
        this.membersAge[memberIndex] = null;
        this.membersGender[memberIndex] = null;
    }


    private Integer getFreeMemberIndex() {
        for (int i = 0; i < this.memberCapacityIndex.length; i++) {
            if (memberCapacityIndex[i])
                return i;
        }

        System.out.println("there isn't any capacity to add member! \n");
        return -1;
    }

    private Integer getMemberIndex() {
        System.out.print("Enter Member Id : ");
        int memberIndex = scanner.nextInt() - 1;
        if (!this.checkMemberIndex(memberIndex)) {
            System.out.println("Member id is not valid! \n");
            return -1;
        }

        return memberIndex;
    }

    private boolean checkMemberIndex(Integer memberIndex) {
        return memberIndex >= 0 && memberIndex <= this.memberCapacityIndex.length;
    }
}
