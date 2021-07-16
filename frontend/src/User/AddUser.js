import React, {useState} from 'react';
import {Button, Form} from 'react-bootstrap';
import axios from "axios";
import {useHistory} from "react-router-dom";



function AddUser() {
    const history = useHistory();

    const [user, setUser] = useState({});

    const sendUser=(e) =>{
        e.preventDefault();

        console.log(user)

        axios.post('/users/sign-up', user)
            .catch((err) => {
                console.log(err);
            });
        history.push("/users");
    }


    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicUsername">
                    <Form.Label>Username</Form.Label>
                    <Form.Control onChange={(e)=>setUser({username: e.target.value, password: user.password})}  type="username" placeholder={"username"}/>
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e)=>setUser({username: user.username, password: e.target.value})}  type="password" placeholder={"password"}/>
                </Form.Group>
                <Button onClick={(e)=>sendUser(e)} variant="primary" type="submit">
                    Create
                </Button>
            </Form>
        </div>
    )
};


export default AddUser;