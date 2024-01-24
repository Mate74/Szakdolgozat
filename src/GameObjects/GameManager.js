import { State } from "./Entity";
import EntityFactory from "./EntityFactory";
import Team from "./Team";

export default class GameManager {
    
    constructor(scene)
    {
        this.scene=scene;
        this.teams= [];
    }

    initGameManager(team1x,team1y,team2x,team2y)
    {
        this.teams.push(new Team(team1x+100,team1y))
        this.teams.push(new Team(team2x-100,team2y))
        let tower1=this.scene.add.image(team1x,team1y,'tower').setDepth(1).setScale(0.1,0.1)
        let tower2=this.scene.add.image(team2x,team2y,'tower').setDepth(1).setScale(0.1,0.1)
        
        console.log(this.teams);


        tower1.setInteractive();
        tower1.on("pointerdown",()=>{
            EntityFactory.warriorcreator(this.scene,this.teams[0],Teamtype.red,-1);
        })

        tower2.setInteractive();
        tower2.on("pointerdown",()=>{
            EntityFactory.warriorcreator(this.scene,this.teams[1],Teamtype.blue,1);
        })


        this.scene.matter.world.on('collisionstart', (event,bodyA,bodyB )=>{
            console.log("collisionstart")
            let foundEntities=this.findEntities(bodyA,bodyB);
   
             if(foundEntities[0].team==foundEntities[1].team){
                foundEntities[0].velocity=0;
                foundEntities[1].velocity=0;
                if(foundEntities[0].team==Teamtype.red)
                {
                   if(bodyA.position.x<bodyB.position.x)
                   {
                    foundEntities[0].stateChange(State.Idle)
                   }
                   else{
                    foundEntities[1].stateChange(State.Idle)
                   }
                }
                else{
                    if(bodyA.position.x>bodyB.position.x)
                    {
                     foundEntities[0].stateChange(State.Idle)
                    }
                    else{
                        foundEntities[1].stateChange(State.Idle)
                    }
                }
             }
             else{
                /* foundEntities.forEach((entity)=>{
                    if(entity.state==State.Move)
                    {
                        entity.state=State.Attack;
                        console.log(entity.state); 
                    }
    
                }) */

                foundEntities[0].stateChange(State.Attack)
                foundEntities[1].stateChange(State.Attack)

                foundEntities[0].attackTimer=this.scene.time.addEvent({
                    delay: 500,                // ms
                    callback: foundEntities[0].attack,
                    args: [foundEntities[1]],
                    callbackScope: foundEntities[0],
                    loop: true
                });
                foundEntities[1].attackTimer=this.scene.time.addEvent({
                    delay: 500,                // ms
                    callback: foundEntities[1].attack,
                    args: [foundEntities[0]],
                    callbackScope: foundEntities[1],
                    loop: true
                });
                console.log(foundEntities[0].attackTimer);
             }
        })
        
        this.scene.matter.world.on('collisionend', (event,bodyA,bodyB )=>{
            console.log("collisionend")
            console.log(bodyA.id)
            console.log(bodyB.id)
            let foundEntities=this.findEntities(bodyA,bodyB);
            
            if(foundEntities[0].team==foundEntities[1].team){
                if(foundEntities[0].team==Teamtype.red)
                {
                   if(bodyA.position.x<bodyB.position.x)
                   {
                    if(bodyA.state==State.Move)
                    {
                        bodyB.stateChange(State.Move)
                    }
                   }
                   else{
                    if(bodyB.state==State.Move)
                    {
                        bodyA.stateChange(State.Move)
                    }
                   }
                }
                else{
                    if(bodyA.position.x>bodyB.position.x)
                    {
                     foundEntities[0].stateChange(State.Idle)
                    }
                    else{
                        foundEntities[1].stateChange(State.Idle)
                    }
                }
            }
            else{
                console.log(event)
                foundEntities.forEach((entity)=>{
                    if(entity.state==State.Idle)
                    {
                        entity.stateChange(State.Move)
                    }
                })
            }

        })
    }
    findEntities(bodyA,bodyB){
        
        let foundEntities=[null,null];
        this.teams.forEach((team)=>{
            team.entities.forEach((entity)=>{
                //TODO break that shit, optimalizálás
                if(entity.id==bodyA.id )
                {   
                    foundEntities[0]=entity;
                }
                if(entity.id==bodyB.id)
                {
                    foundEntities[1]=entity;
                }
            })
         })
         return foundEntities;
    }
}
const Teamtype={
    red:0,
    blue:1
}