class TaskManager
  Task = Struct.new(:id, :description)
  attr_reader :tasks

  def initialize
    @tasks = []
  end

  def add_task
    puts 'Enter ID:'
    id = gets.chomp.to_i

    puts 'Enter Description:'
    description = gets.chomp

    @tasks << Task.new(id, description)
    puts 'Task added!'
  end

  def show_tasks
    puts 'All Tasks:'
    @tasks.each { |task| puts "ID: #{task.id} | Description: #{task.description}" }
  end

  def delete_task(id)
    task = @tasks.find { |t| t.id == id }
    if task
      @tasks.delete(task)
      puts 'Task deleted!'
    else
      puts 'Task not found!'
    end
  end
end

task_manager = TaskManager.new

loop do
  puts "\nChoose operation:"
  puts '1. Add Task'
  puts '2. Show Tasks'
  puts '3. Delete Task'
  puts '4. Exit'

  choice = gets.chomp.to_i

  case choice
  when 1
    task_manager.add_task
  when 2
    task_manager.show_tasks
  when 3
    puts 'Enter Task ID to delete:'
    id = gets.chomp.to_i
    task_manager.delete_task(id)
  when 4
    puts 'Exiting program...'
    break
  else
    puts 'Invalid choice! Try again.'
  end
end
