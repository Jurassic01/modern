<?php
class Task {
    public $id;
    public $description;

    public function __construct($id, $description) {
        $this->id = $id;
        $this->description = $description;
    }
}

class TaskManager {
    public $tasks = [];

    public function addTask() {
        echo "Enter ID: ";
        $id = trim(fgets(STDIN));

        echo "Enter Description: ";
        $description = trim(fgets(STDIN));

        $task = new Task($id, $description);
        $this->tasks[] = $task;
        echo "Task added!\n";
    }

    public function showTasks() {
        echo "All Tasks:\n";
        foreach ($this->tasks as $task) {
            echo "ID: $task->id | Description: $task->description\n";
        }
    }

    public function deleteTask($id) {
        foreach ($this->tasks as $key => $task) {
            if ($task->id == $id) {
                unset($this->tasks[$key]);
                echo "Task deleted!\n";
                return;
            }
        }
        echo "Task not found!\n";
    }
}

$taskManager = new TaskManager();

while (true) {
    echo "\nChoose operation:\n";
    echo "1. Add Task\n";
    echo "2. Show Tasks\n";
    echo "3. Delete Task\n";
    echo "4. Exit\n";
    echo "Enter your choice: ";

    $choice = trim(fgets(STDIN));

    switch ($choice) {
        case 1:
            $taskManager->addTask();
            break;
        case 2:
            $taskManager->showTasks();
            break;
        case 3:
            echo "Enter Task ID to delete: ";
            $id = trim(fgets(STDIN));
            $taskManager->deleteTask($id);
            break;
        case 4:
            echo "Exiting program...\n";
            exit;
        default:
            echo "Invalid choice! Try again.\n";
            break;
    }
}
?>
