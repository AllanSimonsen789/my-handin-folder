import React, { useState } from "react";

function MemberTable({ members }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Age</th>
        </tr>
      </thead>
      <tbody>
        {members.map((m, index) => {
          return (
            <tr key={index}>
              <td>{m.name}</td>
              <td>{m.age}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
}

function MemberDemo(props) {
  console.log(props.members)
  return (
    <div>

      <MemberTable members={props.members} />
    </div>
  );
}

export default function App() {
  const initialMembers = [{ name: "Peter", age: 18 },
  { name: "Jan", age: 35 },
  { name: "Janne", age: 25 },
  { name: "Martin", age: 22 }];
  const [members, setMembers] = useState(initialMembers)

  return (<MemberDemo members={members} />);
}
