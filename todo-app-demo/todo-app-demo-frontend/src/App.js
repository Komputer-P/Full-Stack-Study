import './App.css';
import { useEffect, useState } from 'react';
import TodoItem from './components/TodoItem';

function App() {
  const [todoItems, setTodoItems] = useState(null);

  // do something on load
  useEffect(() => {
    console.log("loaded");
    if (!todoItems) {
      fetch('http://localhost:8080/api/todoItems')
        .then((response) => response.json())
        .then((data) => {
          console.log("Got Todo Items: ", data);
          setTodoItems(data);
        });
    }
  });

  function addNewTodoItem() {
    fetch('http://localhost:8080/api/todoItems', {
      method : 'POST',
      headers: {
        'content-type' : 'application/json'
      }
    })
      .then((response) => response.json())
      .then((aTodoItem) => {
        console.log("New Todo Item: ", aTodoItem);
        setTodoItems([...todoItems, aTodoItem]);
      })
  }

  function handleDeleteTodoItem(item) {
    const updatedTodoItems = todoItems.filter(aTodoItem => aTodoItem.id !== item.id);
    setTodoItems([...updatedTodoItems]);
  }

  return (
    <>
      <div>
        <button onClick={addNewTodoItem}>Add New Item</button>
      </div>
      <div>
        {todoItems != null ? todoItems.map((todoItem) => {
          return <TodoItem key={todoItem.id} data={todoItem} emitDeleteTodoItem={handleDeleteTodoItem} />;
        }) : "loading data..."}
      </div>
    </>
  )
}

export default App;
