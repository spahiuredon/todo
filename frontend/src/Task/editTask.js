import React, {useEffect, useState} from 'react';
import {Table, Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useParams, useHistory} from "react-router-dom";



function EditTask() {
    const history = useHistory();

    const {id} = useParams();

    const [task, setTask] = useState({});

    const sendTask=(e) =>{
        e.preventDefault();

        console.log(task)

        axios.put('/tasks/'+id, task)
            .catch((err) => {
                console.log(err);
            });
        history.push("/tasks");
    }

    useEffect(() => {
        console.log(id)
        axios.get("/tasks/"+id)
            .then((res) => {
                setTask(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);
    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Title</Form.Label>
                    <Form.Control onChange={(e)=>setTask({title: e.target.value, description: task.description})}  type="title" value={task.title}/>
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Description</Form.Label>
                    <Form.Control onChange={(e)=>setTask({description: task.description, title: e.target.value})}  type="description" value={task.description}/>
                </Form.Group>
                <Button onClick={(e)=>sendTask(e)} variant="primary" type="submit">
                    Change
                </Button>
            </Form>
        </div>
    )
};


export default EditTask;