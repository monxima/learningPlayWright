async function getTestResults(){
    return new Promise.resolve("abc");
}

async function main(){
    let results = await getTestResults();
   
}

async function testAPI(){
    try {
        let result = await Promise.reject("503 Service Unavailable");
        console.log('Result', result);
    } catch (error) {
        console.log('Error', error);
    } finally {
        console.log("Clean up!!");
    }
}

testAPI();