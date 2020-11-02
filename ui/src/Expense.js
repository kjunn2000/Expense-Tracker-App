import React , {useState, useEffect } from 'react';
import {Form,Button,Container,Table} from 'react-bootstrap';
import DatePicker from "react-datepicker";
import Moment from 'react-moment';
import "react-datepicker/dist/react-datepicker.css";

function Expense(props) {

    const [ loading , setLoading ] = useState(true);
    const [ categories , setCategories] = useState([]);
    const [ expenses , setExpenses ] = useState([]);

    const [item, setItem ] = useState({
        description : '' ,
        expenseDate : new Date(),
        location : '',
        category : {cid: "101",name:"Underwear"}
    })

    const fetchData = async () => {
        const response= await fetch('/api/categories');
        const body= await response.json();

        setCategories(body);

        console.log(categories);

        const responseExp= await fetch('/api/expenses');
        const bodyExp = await responseExp.json();
        setExpenses( bodyExp);
    
        console.log(expenses);

        setLoading(false);

    }


    useEffect(async () => {
        fetchData();
    },loading);
    

    const handleChange = (event) => {
        const target= event.target;
        const value= target.value;
        const name = target.name;
        
        if (name==="description"){
            setItem({...item,  description:value});
        }else if (name === "location"){
            setItem({...item, location:value});
        }else {
            setItem({...item, category :{cid:value,name:name}});
        }
    }


    const handleDateChange = (date) => {
        setItem({...item, expenseDate:date});
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(item);
        submit();
        fetchData();
    }

    const submit = async () => {
      
        await fetch('/api/expense', {
          method : 'POST',
          headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body : JSON.stringify(item),
        });

        console.log("Successful save.")
    
    }

    let optionList  =
    categories.map( (category) =>
        <option value={category.cid} key={category.cid} name={category.name}>
            {category.name} 
        </option>
    )

    let rows=
    expenses.map( expense =>
    <tr key={expense.eid}>
        <td>{expense.description}</td>
        <td>{expense.location}</td>
        <td><Moment date={expense.expensedate} format="YYYY/MM/DD"/></td>
        <td>{expense.category.name}</td>
        <td><Button size="sm" variant="danger" onClick={() => remove(expense.eid)}>Delete</Button></td>

    </tr>)

    const remove = async (id)=> {
    await fetch(`/api/expense/${id}` , {
      method: 'DELETE' ,
      headers : {
        'Accept' : 'application/json',
        'Content-Type' : 'application/json'
      }

    }).then(() => {
    //   let updatedExpenses = expenses.filter(i => i.id !== id);
    //   setExpenses({expenses: updatedExpenses});
    fetchData();
    });

}


    if (loading)
    return(<div>Loading....</div>)


    return (
        <div className="p-5 ">
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formBasicExpenseDate">
                    <Form.Label>Expense Date</Form.Label>
                    <br/>
                    <DatePicker selected={item.expenseDate} name ="expenseDate" onChange={handleDateChange} />
                    <Form.Text className="text-muted">
                    Please enter the correct expense date.
                    </Form.Text>
                </Form.Group>

                <Form.Group controlId="formBasicDescription">
                    <Form.Label>Description</Form.Label>
                    <Form.Control name ="description" type="text" placeholder="Description" onChange={handleChange} />
                </Form.Group>
                <Form.Group controlId="formBasicLocation">
                    <Form.Label>Location</Form.Label>
                    <Form.Control name = "location" type="text" placeholder="Location" onChange={handleChange} />
                </Form.Group>
                <Form.Group controlId="formBasicCategory">
                    <Form.Label>Category</Form.Label>
                <Form.Control name = "category" className="col-4" as="select" onChange={handleChange}>
                        {optionList}
                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>


            <Container className="pt-5">
                <h3>Expense List</h3>
                <Table className="mt-4">
                <thead>
                  <tr>
                    <th width="30%">Description</th>
                    <th width="10%">Location</th>
                    <th> Date</th>
                    <th> Category</th>
                    <th width="10%">Action</th>
                  </tr>
                </thead>
                <tbody>
                   {rows}
                </tbody>

                </Table>
              </Container>
        </div>
    );
}

export default Expense;