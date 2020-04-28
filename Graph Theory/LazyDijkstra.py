import heapq

'''
lazy_dijkstra_sssp function takes 2 params
adj_list: The adjecency list representing the graph
Each index in adjecency list represents the node, and each index contains a list that contains connected nodes in the form of a tuple (node, edge_weight)
start_node: the source node to start from

The function returns a list containing the distance of each node from the source node
'''
def lazy_dijkstra_sssp(adj_list, start_node):
    n = len(adj_list)
    
    '''
    a list to store the distance, initialized to None except first element
    '''
    dist = [0 if i == start_node else None for i in range(n)]
    
    visited = [False for i in range(n)]
    
    priority_q = []
    
    heapq.heappush(priority_q, (0, start_node))
    
    while len(priority_q):
        ''' fetch the current node '''
        print(priority_q)
        
        c = heapq.heappop(priority_q)
        current = c[1]
        
        visited[current] = True
        
        if c[0] > dist[current]:
            continue
        
        for i in adj_list[current]:
            if visited[i[0]]:
                continue
            d = dist[current] + i[1]
            dist[i[0]] = d if dist[i[0]] == None else min(d, dist[i[0]])
            if dist[i[0]] == d:
                heapq.heappush(priority_q, (dist[i[0]], i[0]))
            
    return dist
    
# Testing

adj_list = [
        [(1, 4), (2, 1)],
        [(3, 1)],
        [(1, 2), (3, 5)],
        [(4, 3)],
        []
    ]

print(lazy_dijkstra_sssp(adj_list, 0))
