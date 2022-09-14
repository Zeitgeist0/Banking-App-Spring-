export default async function fetchAllCustomers() {
  try {
    let response;
    response = await fetch("http://localhost:9000/customers/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const customers = await response.json();
    return customers;
  } catch (err) {
    console.log(err);
  }
}
