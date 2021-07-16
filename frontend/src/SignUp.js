import React, {useState} from "react";
import axios from "axios";
import {Button, Form} from "react-bootstrap";
import {useHistory} from "react-router-dom";


function SignUp(){
    const history = useHistory();

    const [user, setUser] = useState({
        username:"",
        password: ""
    });

    const sendUser=(e) =>{
        e.preventDefault();

        console.log(user)

        axios.post('/users', user)
            .catch((err) => {
                console.log(err);
            });
        history.push("/users")
    }

    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicUsername">
                    <Form.Label>Username</Form.Label>
                    <Form.Control onChange={(e)=>setUser({username: e.target.value, password: user.password})} type="username" placeholder="Username"/>
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e)=>setUser({username: user.username, password: e.target.value})} type="password" placeholder="Password"/>
                </Form.Group>
                <Button variant="primary" type="submit" onClick={(e)=>sendUser(e)}>
                    Submit
                </Button>
            </Form>
        </div>
    );
}
export default SignUp;