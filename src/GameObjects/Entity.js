export default class Entity {
    
    constructor(scene,sprite,damage,hp,velocity,cost,team)
    {
        this.scene=scene;
        this.sprite=sprite;
        this.damage=damage;
        this.hp=hp;
        this.velocity=velocity;
        this.cost=cost;
        this.id=sprite.body.id; 
        this.state=State.Move;
        this.team=team;
        this.attackTimer=null;
        sprite.setInteractive();
        sprite.on("pointerdown",()=>{
            console.log(this.id)
        })
    }

    moveAction()
    {
        this.sprite.anims.play('orc_walk',true);
        const speed=-2.5;
        let Velocity= new Phaser.Math.Vector2();
        if(true) {

            Velocity.x=this.velocity;
         }


        Velocity.scale(speed)
         this.sprite.setVelocity(Velocity.x,Velocity.y)
    }
    brain()
    {
        switch(this.state)
        {
            case State.Move:
                this.moveAction();
                break;
            case State.Attack:
                this.sprite.anims.play('orc_idle',true);
                break;
            case State.Idle:
                this.sprite.anims.play('orc_idle',true);
                break;
            case State.Dead:
                break;


        }
    }
    attack(enemy){
        enemy.hp-=this.damage;
        if(enemy.hp<=0)
        {
            this.attackTimer.remove();
            this.attackTimer=null;
            enemy.attackTimer.remove();
            enemy.attackTimer=null;
            enemy.stateChange(State.Dead);
            enemy.kill();
            this.stateChange(State.Move);
            console.log(this.attackTimer);
        }
    }
    kill()
    {
        
		 this.scene.matter.world.remove(this.sprite)
         this.sprite.destroy();
        /* console.log(this.sprite) */
    }
    stateChange(state)
    {
        console.log(`entityid:${this.id}  from:${this.state} to: ${state}`);
        this.state=state;
        if(this.state!=State.Move)
        {
            this.sprite.body.frictionAir=1
        }
    }
}
export const State={
    Move:'Move',
    Attack:'Attack',
    Idle:'Idle',
    Dead:'Dead'
}