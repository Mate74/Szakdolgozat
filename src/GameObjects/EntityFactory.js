
import Warrior from "./Warrior"

export default class EntityFactory{
    
    static warriorcreator(scene,team,teamtype,sign)
    {
        let sprite=new Phaser.Physics.Matter.Sprite(scene.matter.world,team.x,team.y,'orc','orc0').setDepth(2)
        
        team.entities.push(new Warrior(scene,sprite,Math.floor(Math.random() * 5+1)+1,80,sign*1,0,teamtype))
        scene.add.existing(sprite)
        console.log(sprite)

    }
}
