package org.example;

import java.util.*;
import java.util.function.Function;

public class Main {
    private static List<TaskItem> tasks = new ArrayList<>();

    private void initialiseList() {
        tasks.add(new TaskItem(1, "read the WSS docs", Progress.NOT_DONE));
        tasks.add(new TaskItem(2, "get VPN access", Progress.NOT_DONE));
        tasks.add(new TaskItem(3, "work on small tools to improve knowledge in Java", Progress.IN_PROGRESS));
        tasks.add(new TaskItem(4, "setup dev environment", Progress.DONE));
    }

    public static void main(String[] args) {

//        new Main().initialiseList();
        TaskDbService taskDbService = new TaskDbService();

//        intro();
        switch (args[0]) {
            case "list":
                if (args.length > 1) {
                    taskDbService.getTask(args[1]);
                } else {
                    taskDbService.getTasks();
                }
                break;
            case "add":
                taskDbService.addTask(args[1]);
                break;
            case "update":
                updateItem(args[1], args[2]);
                break;
            case "delete":
                deleteItem(args[1]);
                break;
            case "mark-done":
                markDone(args[1]);
                break;
            case "mark-in-progress":
                markInProgress(args[1]);
                break;
            default:
                System.out.println("command not recognised");
                break;
        }
    }

    /**
     * Updates task with id taskId with newTitle.
     * @param taskId
     * @param newTitle
     */
    public static void updateItem(String taskId, String newTitle) {
    }

    public static void intro() {
        String docs = """
                Available options are:
                list: returns list of all tasks
                list done: returns all 'done' tasks
                list not-done: returns all 'not-done' tasks
                list in-progress: returns all 'in-progress' tasks
                add <title>: create a new task with title
                mark-in-progress <TaskId>: set task with TaskId as 'In Progress'
                mark-done <TaskId>: set task with TaskId as 'Done'
                delete <TaskId>: deletes task with TaskId
                """;
        System.out.println(docs);
    }

    /**
     * Mark a task with id taskId as 'In Progress' state.
     * @param taskId
     */
    public static void markInProgress(String taskId) {
        headLiner("Mark item in-progress");
    }

    /**
     * Mark a task with id taskId as 'Done' state.
     * @param taskId
     */
    public static void markDone(String taskId) {
        headLiner("Mark item done");

    }

    /**
     * Delete task with id taskId.
     * @param taskId
     */
    public static void deleteItem(String taskId) {
        headLiner("Deleting item");
        tasks.remove(getItem.apply(taskId).get());
        listItems();
    }

    /**
     * Create new task with given title.
     * @param taskTitle
     */
    public static void addItem(String taskTitle) {
        tasks.add(new TaskItem(tasks.size() + 1, taskTitle, Progress.NOT_DONE));
        listItems();
    }

    /**
     * List items according to their criteria of progress.
     * @param criteria
     */
    public static void listItems(String criteria) {
        headLiner("Listing items");
        System.out.printf("Checking criteria for %s%n", criteria);
        switch (criteria) {
            case "done" -> tasks.stream().filter(taskItem -> {
                return taskItem.getProgress().equals(Progress.DONE);
            }).forEach(System.out::println);
            case "not-done" -> tasks.stream().filter(taskItem -> {
                return taskItem.getProgress().equals(Progress.NOT_DONE);
            }).forEach(System.out::println);
            case "in-progress" -> tasks.stream().filter(taskItem -> {
                return taskItem.getProgress().equals(Progress.IN_PROGRESS);
            }).forEach(System.out::println);
            default -> listItems();
        }
    }

    /**
     * List all items in collection.
     */
    public static void listItems() {
        headLiner("Listing items");
        tasks.forEach(System.out::println);
    }

    public static void headLiner(String headline) {
        System.out.print(headline.indent(2));
        System.out.println("*".repeat(headline.length() + 4));
    }


    public static final Function<String, Optional<TaskItem>> getItem = taskId -> tasks.stream()
            .filter(t -> t.getId() == Integer.parseInt(taskId))
            .findFirst();
}