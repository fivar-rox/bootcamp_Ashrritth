import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';


const GrpList = () => {
  const [groups, setGroups] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('/grocery/findAllItems')
      .then(response => response.json())
      .then(data => {
        setGroups(data);
        setLoading(false);
      })
  }, []);

  const remove = async (id) => {
    await fetch(`/grocery/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedGroups = [...groups].filter(i => i.id !== id);
      setGroups(updatedGroups);
    });
  }

  const removeAll = async () => {
    await fetch(`/grocery/deleteAll`,{
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedGroups = [];
      setGroups(updatedGroups);

    })
  }
  if (loading) {
    return <p>Loading...</p>;
  }

  const groupList = groups.map(group => {
    // const address = `${group.address || ''} ${group.city || ''} ${group.stateOrProvince || ''}`;
    return <tr key={group.id}>
      <td style={{whiteSpace: 'nowrap'}}>{group.name}</td>
      <td style={{whiteSpace: 'nowrap'}}>{group.price.toFixed(1)}</td>
      <td style={{whiteSpace: 'nowrap'}}>{group.description}</td>
      <td>
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/grocery/" + group.id}>Edit</Button>
          <Button size="sm" color="danger" onClick={() => remove(group.id)}>Delete</Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <div className="float-end">
          <th align="left" width="30%"><Button color="success" tag={Link} to="/grocery/new">Add Grocery+</Button></th>
          <th align="right" width="30%"><Button  color="danger" onClick={() => removeAll()}>Delete all</Button></th>
        </div>
        <h3>My Grocery List</h3>
        <Table className="mt-4">
          <thead>
          <tr>
            <th align = 'left' width = "10%" >Name</th>
            <th align = 'left' width = "5%" >Price</th>
            <th align = 'left' width = "10%" >Description</th>
            <th align = 'left' width = "10%" >Actions</th>
          </tr>
          </thead>
          <tbody>
          {groupList}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default GrpList;