async function getExample(){
    return "abc";
}

import {test,expect} from '@playwright/test';
test('user say hello', async ({page}) => {
    let t= await getExample();
    console.log(t);
    await page.goto('https://example.com');
});