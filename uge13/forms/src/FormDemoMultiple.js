import React, { useState } from "react";

function ReservationForm() {
    const initialValue = {
        payByCreditCard: true,
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        street: "",
        city: "",
        zip: "",
        country: ""
    };
    const [reservation, setReservation] = useState(initialValue);

    const handleChange = event => {
        const target = event.target;
        const value = target.type === "checkbox" ? target.checked : target.value;
        const name = target.name;
        setReservation({ ...reservation, [name]: value });
    };

    function handleSubmit(event) {
        const {firstName, lastName, email, phone, street, city, zip, country} = reservation
        alert(firstName + " " + lastName + " " +  email + " " +  phone + " " +  street + " " +  city + " " +  zip + " " +  country);
        event.preventDefault();
    
      };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>Pay by Credit Card: </label>
                <input
                    name="payByCreditCard"
                    type="checkbox"
                    checked={reservation.payByCreditCard}
                    onChange={handleChange}
                />
                <br />
                <input
                    name="firstName"
                    value={reservation.firstName}
                    onChange={handleChange}
                    placeholder="First Name"
                />
                <br />
                <input
                    name="lastName"
                    value={reservation.lastName}
                    onChange={handleChange}
                    placeholder="Last Name"
                />
                <br />
                <input
                    name="email"
                    value={reservation.email}
                    onChange={handleChange}
                    placeholder="email"
                />
                <br />
                <input
                    name="phone"
                    value={reservation.phone}
                    onChange={handleChange}
                    placeholder="phone"
                />
                <br />
                <input
                    name="street"
                    value={reservation.street}
                    onChange={handleChange}
                    placeholder="street"
                />
                <br />
                <input
                    name="city"
                    value={reservation.city}
                    onChange={handleChange}
                    placeholder="city"
                />
                <br />
                <input
                    name="zip"
                    value={reservation.zip}
                    onChange={handleChange}
                    placeholder="zip"
                />
                <br />
                <input
                    name="country"
                    value={reservation.country}
                    onChange={handleChange}
                    placeholder="country"
                />
                <input type="submit" value="Submit" />
                <p>{JSON.stringify(reservation)}</p>
            </form>
        </div>
    );
}
export default ReservationForm;