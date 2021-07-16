import React, {useEffect, useState} from 'react';
import {Table, Button} from 'react-bootstrap';
import axios from "axios";
import {Link} from "react-router-dom";

function TaskList() {

    const [tasks, setTasks] = useState([]);

    let count=0;

    const getTasks = () => {
        axios.get("/tasks")
            .then((res) => {
                setTasks(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    useEffect( () => {
        getTasks()
    }, [tasks]);


    const deleteTask = (id) => {
        axios.delete("/tasks/" + id)
            .catch((err) => {
                console.log(err);
            });
        getTasks();
        window.location.reload();
    }

    const taskList = tasks.map(user => {
        return <Table striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Description</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{tasks.id}</td>
                <td>{tasks.title}</td>
                <td>{tasks.description}</td>
                <td>
                    <Link to={"/editTask/"+tasks.id} className="btn btn-warning">Update</Link>
                </td>
                <td>
                    <Button  variant="danger" onClick={() => deleteTask(tasks.id)}>Delete</Button>
                </td>
            </tr>
            </tbody>
        </Table>
    });
    return (
        <div>
            {taskList}
            <div className="float-right">
                <Link to={"/addTask"} className="btn btn-primary  btn-lg btn-block">Add Task</Link>
            </div>
            <br/>
            <div className="float-right">
                <Link  to={"/userList"} className="btn btn-info  btn-lg btn-block">See Users</Link>
            </div>
        </div>
    )};


export default TaskList;