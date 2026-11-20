test('saves', () => { 
    save().then(r => expect(r.ok).toBe(true)); 
}) 


 it('loads', async (done) => { 
    const d = await load(); 
    expect(d).toBeTruthy(); 
    done(); }) 