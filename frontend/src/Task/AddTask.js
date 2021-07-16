import React, {useState} from 'react';
import {Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useHistory} from "react-router-dom";



function AddTask() {
    const history = useHistory();

    const [task, setTask] = useState({});

    const sendTask=(e) =>{
        e.preventDefault();

        console.log(task)

        axios.post('/tasks', task)
            .catch((err) => {
                console.log(err);
            });
        history.push("/tasks");
    }


    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Title</Form.Label>
                    <Form.Control onChange={(e)=>setTask({title: e.target.value, description: task.description})}  type="title" placeholder={"Title"}/>
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Description</Form.Label>
                    <Form.Control onChange={(e)=>setTask({description: task.description, title: e.target.value})}  type="description" placeholder={"Description"}/>
                </Form.Group>
                <Button onClick={(e)=>sendTask(e)} variant="primary" type="submit">
                    Create
                </Button>
            </Form>
        </div>
    )
};


export default AddTask;